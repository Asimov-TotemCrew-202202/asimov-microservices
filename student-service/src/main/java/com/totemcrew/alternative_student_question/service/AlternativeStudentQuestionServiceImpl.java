package com.totemcrew.alternative_student_question.service;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totemcrew.alternative_student_question.client.ExamDetailClient;
import com.totemcrew.alternative_student_question.domain.model.entity.AlternativeStudentQuestion;
import com.totemcrew.alternative_student_question.domain.persistence.AlternativeStudentQuestionRepository;
import com.totemcrew.alternative_student_question.domain.service.AlternativeStudentQuestionService;
import com.totemcrew.grades.domain.model.entity.Grade;
import com.totemcrew.grades.domain.persistence.GradeRepository;
import com.totemcrew.sections.domain.model.entity.Section;
import com.totemcrew.shared.exception.ResourceNotFoundException;
import com.totemcrew.shared.exception.ResourceValidationException;
import com.totemcrew.students.domain.persistence.StudentRepository;

@Service
public class AlternativeStudentQuestionServiceImpl implements AlternativeStudentQuestionService{

    private static final String ENTITY = "AlternativeStudentQuestion";

    @Autowired
    private AlternativeStudentQuestionRepository alternativeStudentQuestionRepository;

    @Autowired
    private Validator validator;

    @Autowired
    ExamDetailClient examDetailClient;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public AlternativeStudentQuestion getById(Long alternativeId) {
        return alternativeStudentQuestionRepository.findById(alternativeId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, alternativeId));
    }

    @Override
    public AlternativeStudentQuestion create(AlternativeStudentQuestion alternative, Long studentId, Long examDetailId) {
        Set<ConstraintViolation<AlternativeStudentQuestion>> violations = validator.validate(alternative);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        var existingExamDetail =  examDetailClient.getExamDetailById(examDetailId);
        if (existingExamDetail == null) 
           throw new ResourceNotFoundException("Exam detail ", examDetailId);

         return studentRepository.findById(studentId).map(student -> {
                alternative.setStudent(student);
                alternative.setExamDetailId(examDetailId);
                alternative.setIsCorrect(false);

                if (alternative.getCheckedAlternative() == existingExamDetail.getCorrectOptionOrder())
                    alternative.setIsCorrect(true);

                return alternativeStudentQuestionRepository.save(alternative);
            }).orElseThrow(()->new ResourceNotFoundException("Student ", studentId));
    }
}

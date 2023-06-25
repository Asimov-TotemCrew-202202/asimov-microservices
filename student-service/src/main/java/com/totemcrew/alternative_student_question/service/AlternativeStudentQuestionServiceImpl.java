package com.totemcrew.alternative_student_question.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totemcrew.alternative_student_question.client.ExamDetailClient;
import com.totemcrew.alternative_student_question.domain.model.entity.AlternativeStudentQuestion;
import com.totemcrew.alternative_student_question.domain.persistence.AlternativeStudentQuestionRepository;
import com.totemcrew.alternative_student_question.domain.service.AlternativeStudentQuestionService;
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
    public List<AlternativeStudentQuestion> getByStudentIdAndExamId(Long studentId, Long examId) {
        var existingAlternatives =  alternativeStudentQuestionRepository.findByStudentIdAndExamId(studentId, examId);
        if(existingAlternatives.isEmpty())
            throw new ResourceNotFoundException(ENTITY, examId);
        return existingAlternatives;
    }

    @Override
    public AlternativeStudentQuestion create(AlternativeStudentQuestion alternative, Long studentId, Long examDetailId) {
        System.out.println("here" + studentId + examDetailId);

        Set<ConstraintViolation<AlternativeStudentQuestion>> violations = validator.validate(alternative);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        var existingExamDetail =  examDetailClient.getExamDetailById(examDetailId);
        if (existingExamDetail == null) 
           throw new ResourceNotFoundException("Exam detail ", examDetailId);

        var existingAlternative =  alternativeStudentQuestionRepository.findByStudentIdAndExamDetailId(studentId, examDetailId);
        if (existingAlternative != null) 
           throw new ResourceValidationException("Alternative already exists with same studentId and examDetailId");

         return studentRepository.findById(studentId).map(student -> {
                alternative.setStudent(student);
                alternative.setExamDetailId(examDetailId);
                alternative.setIsCorrect(false);
                alternative.setExamId(existingExamDetail.getExamId());

                if (alternative.getCheckedAlternative() == existingExamDetail.getCorrectOptionOrder())
                    alternative.setIsCorrect(true);

                return alternativeStudentQuestionRepository.save(alternative);
            }).orElseThrow(()->new ResourceNotFoundException("Student ", studentId));
    }
}

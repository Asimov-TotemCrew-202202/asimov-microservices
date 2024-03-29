package com.totemcrew.alternative_student_question.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totemcrew.alternative_student_question.client.ExamClient;
import com.totemcrew.alternative_student_question.domain.model.entity.AlternativeStudentQuestion;
import com.totemcrew.alternative_student_question.domain.persistence.AlternativeStudentQuestionRepository;
import com.totemcrew.alternative_student_question.domain.service.AlternativeStudentQuestionService;
import com.totemcrew.scores.domain.model.Score;
import com.totemcrew.scores.domain.persistence.ScoreRepository;
import com.totemcrew.scores.domain.service.ScoreService;
import com.totemcrew.shared.exception.ResourceNotFoundException;
import com.totemcrew.shared.exception.ResourceValidationException;
import com.totemcrew.students.domain.persistence.StudentRepository;

@Service
public class AlternativeStudentQuestionServiceImpl implements AlternativeStudentQuestionService{

    private static final String ENTITY = "AlternativeStudentQuestion";
    private static final float BASE_SCORE = 20;

    @Autowired
    private AlternativeStudentQuestionRepository alternativeStudentQuestionRepository;

    @Autowired
    private Validator validator;

    @Autowired
    ExamClient examClient;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private ScoreService scoreService;

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

        Set<ConstraintViolation<AlternativeStudentQuestion>> violations = validator.validate(alternative);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        var existingExamDetail =  examClient.getExamDetailById(examDetailId);
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

                // crea clase si no existe
                var existingScore = scoreRepository.findByStudentIdAndExamId(studentId, existingExamDetail.getExamId());
                if (existingScore == null) {
                    Score request = new Score();
                    existingScore = scoreService.create(request, studentId, (existingExamDetail.getExamId()));
                }
                int points = existingScore.getPoints();
                int numberQuestions = existingScore.getNumberQuestions();

                // si existe añade un punto si la respuesta es correcta, y siempre añade un punto al total de preguntas
                if (alternative.getCheckedAlternative() == existingExamDetail.getCorrectOptionOrder()) {
                    alternative.setIsCorrect(true);
                    points = points + 1;
                }
                numberQuestions = numberQuestions + 1;
                existingScore.setPoints(points);
                existingScore.setNumberQuestions(numberQuestions);
                existingScore.setFinalScore((Float.valueOf(points) / Float.valueOf(numberQuestions)) * BASE_SCORE);
                scoreService.update(existingScore, existingScore.getId());

                return alternativeStudentQuestionRepository.save(alternative);
            }).orElseThrow(()->new ResourceNotFoundException("Student ", studentId));
    }
}

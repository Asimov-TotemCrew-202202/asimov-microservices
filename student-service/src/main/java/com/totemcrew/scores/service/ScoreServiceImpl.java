package com.totemcrew.scores.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totemcrew.alternative_student_question.client.ExamClient;
import com.totemcrew.scores.domain.model.Score;
import com.totemcrew.scores.domain.persistence.ScoreRepository;
import com.totemcrew.scores.domain.service.ScoreService;
import com.totemcrew.shared.exception.ResourceNotFoundException;
import com.totemcrew.shared.exception.ResourceValidationException;
import com.totemcrew.students.domain.persistence.StudentRepository;

@Service
public class ScoreServiceImpl implements ScoreService {

    private static final String ENTITY = "Score";

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private Validator validator;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    ExamClient examClient;

    @Override
    public Score getById(Long id) {
        var score = scoreRepository.findById(id);
        if(score.isEmpty())
            throw new ResourceNotFoundException(ENTITY, id);

        score.get().setTopicName(examClient.getExamById(score.get().getExamId()).getTopic().getTitle());
        return score.get();
    }

    @Override
    public List<Score> getByStudentId(Long studentId) {
        var existingStudent =  studentRepository.findById(studentId);
        if(existingStudent.isEmpty())
            throw new ResourceNotFoundException("Student", studentId);

        var score = scoreRepository.findByStudentId(studentId);
        score.forEach(s -> s.setTopicName(examClient.getExamById(s.getExamId()).getTopic().getTitle()));

        return score;
    }

    @Override
    public Score getByStudentIdAndExamId(Long studentId, Long examId) {
        var existingScore =  scoreRepository.findByStudentIdAndExamId(studentId, examId);
        if(existingScore == null)
            throw new ResourceNotFoundException(ENTITY, examId);

        existingScore.setTopicName(examClient.getExamById(existingScore.getExamId()).getTopic().getTitle());
        return existingScore;
    }    

    @Override
    public Score create(Score score, Long studentId, Long examId) {
        Set<ConstraintViolation<Score>> violations = validator.validate(score);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return studentRepository.findById(studentId).map(student -> {
            score.setExamId(examId);
            score.setNumberQuestions(0);
            score.setPoints(0);
            score.setFinalScore(0);
            score.setStudent(student);
            return scoreRepository.save(score);
        }).orElseThrow(()->new ResourceNotFoundException("Student ", studentId));
    }

    @Override
    public Score update(Score request, Long id) {
        Set<ConstraintViolation<Score>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

            return scoreRepository.findById(id).map(score ->
            scoreRepository.save(
                    score.withNumberQuestions(request.getNumberQuestions())
                    .withPoints(request.getPoints())).withFinalScore(request.getFinalScore())
    ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }
}

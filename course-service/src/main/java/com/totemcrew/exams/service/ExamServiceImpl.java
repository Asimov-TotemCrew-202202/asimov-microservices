package com.totemcrew.exams.service;

import com.totemcrew.exams.domain.model.entity.Exam;
import com.totemcrew.exams.domain.persistence.ExamRepository;
import com.totemcrew.exams.domain.service.ExamService;
import com.totemcrew.exams.resource.ExamTopicResource;
import com.totemcrew.shared.exception.ResourceNotFoundException;
import com.totemcrew.shared.exception.ResourceValidationException;
import com.totemcrew.topics.domain.persistence.TopicRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class ExamServiceImpl implements ExamService {

    private static final String ENTITY = "Exam";
    private ExamRepository examRepository;
    private TopicRepository topicRepository;
    private Validator validator;

    public ExamServiceImpl(ExamRepository examRepository, TopicRepository topicRepository, Validator validator) {
        this.examRepository = examRepository;
        this.topicRepository = topicRepository;
        this.validator = validator;
    }

    @Override
    public Exam getById(Long examId) {
        var exam = examRepository.findById(examId);
        if(exam.isEmpty())
            throw new ResourceNotFoundException(ENTITY, examId);
        return exam.get();
    }

    @Override
    public Exam getExamByTopicId(Long topicId) {
        var topic = topicRepository.findById(topicId);
        if(topic.isEmpty())
            throw new ResourceNotFoundException("Topic", topicId);
        return examRepository.findByTopicId(topicId);
    }

    @Override
    public Exam create(Exam exam, Long topicId) {
        Set<ConstraintViolation<Exam>> violations = validator.validate(exam);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        var examExist = examRepository.findByTopicId(topicId);
        if (examExist != null)
            throw new ResourceValidationException("Exam already exist");
        return topicRepository.findById(topicId).map(topic -> {
            exam.setTopic(topic);
            return examRepository.save(exam);
        }).orElseThrow(()->new ResourceNotFoundException("Topic ",topicId));
    }

    @Override
    public Exam update(Exam exam, Long topicId) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long topicId) {
        return null;
    }
}

package com.totemcrew.exams.service;

import com.totemcrew.exams.domain.model.entity.ExamDetail;
import com.totemcrew.exams.domain.persistence.ExamDetailRepository;
import com.totemcrew.exams.domain.persistence.ExamRepository;
import com.totemcrew.exams.domain.service.ExamDetailService;
import com.totemcrew.shared.exception.ResourceNotFoundException;
import com.totemcrew.shared.exception.ResourceValidationException;
import com.totemcrew.topics.domain.model.entity.Topic;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class ExamDetailServiceImpl implements ExamDetailService {

    private static final String ENTITY = "Exam Detail";

    private ExamDetailRepository examDetailRepository;
    private ExamRepository examRepository;
    private Validator validator;

    public ExamDetailServiceImpl(ExamDetailRepository examDetailRepository, ExamRepository examRepository, Validator validator) {
        this.examDetailRepository = examDetailRepository;
        this.examRepository = examRepository;
        this.validator = validator;
    }

    @Override
    public List<ExamDetail> getAllByExamId(Long examId) {
        var existingExam =  examRepository.findById(examId);
        if(existingExam.isEmpty())
            throw new ResourceNotFoundException("Exam",examId);
        return examDetailRepository.findByExamId(examId);
    }

    @Override
    public ExamDetail getById(Long id) {
        return examDetailRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, id));
    }

    @Override
    public ExamDetail create(ExamDetail item, Long examId) {
        Set<ConstraintViolation<ExamDetail>> violations = validator.validate(item);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return examRepository.findById(examId).map(exam -> {
            item.setExam(exam);
            return examDetailRepository.save(item);
        }).orElseThrow(()->new ResourceNotFoundException("Exam ",examId));
    }
}

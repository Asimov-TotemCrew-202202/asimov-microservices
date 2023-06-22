package com.totemcrew.grades.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import com.totemcrew.grades.domain.model.entity.Grade;
import com.totemcrew.grades.domain.persistence.GradeRepository;
import com.totemcrew.grades.domain.service.GradeService;
import com.totemcrew.shared.exception.ResourceNotFoundException;
import com.totemcrew.shared.exception.ResourceValidationException;

@Service
public class GradeServiceImpl implements GradeService {

    private static final String ENTITY = "Grade";

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private Validator validator;

    @Override
    public List<Grade> getAll() {
        return gradeRepository.findAll();
    }

    @Override
    public Page<Grade> getAll(Pageable pageable) {
        return gradeRepository.findAll(pageable);
    }

    @Override
    public Grade getById(Long gradeId) {
        return gradeRepository.findById(gradeId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, gradeId));
    }

    @Override
    public Grade create(Grade grade) {
        Set<ConstraintViolation<Grade>> violations = validator.validate(grade);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        var existingName = gradeRepository.findByName(grade.getName());
        if (existingName != null) {
            throw new ResourceValidationException("Grade is already registered");
        }

        return gradeRepository.save(grade);
    }

    @Override
    public Grade update(Long gradeId, Grade request) {
        Set<ConstraintViolation<Grade>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

            return gradeRepository.findById(gradeId).map(grade ->
            gradeRepository.save(
                    grade.withName(request.getName()))
    ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, gradeId));
    }

    @Override
    public ResponseEntity<?> delete(Long gradeId) {
        return gradeRepository.findById(gradeId).map(grade -> {
            gradeRepository.delete(grade);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, gradeId));
    }
}

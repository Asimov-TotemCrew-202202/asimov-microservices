package com.totemcrew.sections.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.totemcrew.grades.domain.persistence.GradeRepository;
import com.totemcrew.sections.domain.model.entity.Section;
import com.totemcrew.sections.domain.persistence.SectionRepository;
import com.totemcrew.sections.domain.service.SectionService;
import com.totemcrew.shared.exception.ResourceNotFoundException;
import com.totemcrew.shared.exception.ResourceValidationException;

@Service
public class SectionServiceImpl implements SectionService {

    private static final String ENTITY = "Section";

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private Validator validator;

    @Override
    public List<Section> getAllByGradeId(Long gradeId) {
        var existingGrade =  gradeRepository.findById(gradeId);
        if(existingGrade.isEmpty())
            throw new ResourceNotFoundException("Grade", gradeId);
        return sectionRepository.findByGradeId(gradeId);
    }

    @Override
    public Page<Section> getAllByGradeId(Long gradeId, Pageable pageable) {
        var existingGrade =  gradeRepository.findById(gradeId);
        if(existingGrade.isEmpty())
            throw new ResourceNotFoundException("Grade", gradeId);
        return sectionRepository.findByGradeId(gradeId, pageable);
    }

    @Override
    public Section getById(Long sectionId) {
        return sectionRepository.findById(sectionId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, sectionId));
    }

    @Override
    public Section create(Long gradeId, Section section) {
        Set<ConstraintViolation<Section>> violations = validator.validate(section);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        
            return gradeRepository.findById(gradeId).map(grade -> {
                section.setGrade(grade);
                return sectionRepository.save(section);
            }).orElseThrow(()->new ResourceNotFoundException("Grade ", gradeId));
    }

    @Override
    public Section update(Long sectionId, Section request) {
        Set<ConstraintViolation<Section>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

            return sectionRepository.findById(sectionId).map(section ->
            sectionRepository.save(
                section.withName(request.getName()))
    ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, sectionId));
    }

    @Override
    public ResponseEntity<?> delete(Long sectionId) {
        return sectionRepository.findById(sectionId).map(section -> {
            sectionRepository.delete(section);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, sectionId));
    }    
}

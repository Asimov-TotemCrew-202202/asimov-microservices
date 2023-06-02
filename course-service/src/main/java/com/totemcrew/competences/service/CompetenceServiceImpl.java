package com.totemcrew.competences.service;

import com.totemcrew.competences.domain.model.entity.Competence;
import com.totemcrew.competences.domain.persistence.CompetenceRepository;
import com.totemcrew.competences.domain.service.CompetenceService;
import com.totemcrew.courses.domain.model.entity.Course;
import com.totemcrew.courses.domain.persistence.CourseRepository;
import com.totemcrew.shared.exception.ResourceNotFoundException;
import com.totemcrew.shared.exception.ResourceValidationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class CompetenceServiceImpl implements CompetenceService {
    private static final String ENTITY = "Course";

    private final CompetenceRepository competenceRepository;
    private final Validator validator;

    public CompetenceServiceImpl(CompetenceRepository competenceRepository, Validator validator) {
        this.competenceRepository = competenceRepository;
        this.validator = validator;
    }

    @Override
    public List<Competence> getAll() {
        return competenceRepository.findAll();
    }

    @Override
    public Page<Competence> getAll(Pageable pageable) {
        return competenceRepository.findAll(pageable);
    }

    @Override
    public Competence getById(Long competenceId) {
        return competenceRepository.findById(competenceId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, competenceId));
    }

    @Override
    public Competence create(Competence competence) {
        Set<ConstraintViolation<Competence>> violations = validator.validate(competence);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return competenceRepository.save(competence);
    }

    @Override
    public Competence update(Long competenceId, Competence competence) {
        Set<ConstraintViolation<Competence>> violations = validator.validate(competence);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return competenceRepository.findById(competenceId).map( data ->
                competenceRepository.save(
                        data.withDescription(competence.getDescription())
                                .withDescription(competence.getDescription()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, competenceId));
    }

    @Override
    public ResponseEntity<?> delete(Long competenceId) {
        return competenceRepository.findById(competenceId).map(data -> {
            competenceRepository.delete(data);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, competenceId));
    }
}

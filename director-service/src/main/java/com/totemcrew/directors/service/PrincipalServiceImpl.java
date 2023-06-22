package com.totemcrew.directors.service;

import com.totemcrew.directors.domain.model.entity.Principal;
import com.totemcrew.directors.domain.persistence.PrincipalRepository;
import com.totemcrew.directors.domain.service.PrincipalService;
import com.totemcrew.shared.exception.ResourceNotFoundException;
import com.totemcrew.shared.exception.ResourceValidationException;
import com.totemcrew.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class PrincipalServiceImpl implements PrincipalService {

    private static final String ENTITY = "Director";
    @Autowired
    private PrincipalRepository principalRepository;
    @Autowired
    private Validator validator;

    @Autowired
    EnhancedModelMapper mapper;

    /*public DirectorServiceImpl(DirectorRepository directorRepository, Validator validator) {
        this.directorRepository = directorRepository;
        this.validator = validator;
    }*/

    @Override
    public List<Principal> getAll() {
        return principalRepository.findAll();
    }

    @Override
    public Page<Principal> getAll(Pageable pageable) {
        return principalRepository.findAll(pageable);
    }

    @Override
    public Principal getById(Long directorId) {
        return principalRepository.findById(directorId)
                .orElseThrow( () -> new ResourceNotFoundException(ENTITY, directorId));
    }

    @Override
    public Principal getByUserId(Long userId) {
        return principalRepository.findByUserId(userId);
    }

    @Override
    public Principal create(Principal director) {
        Set<ConstraintViolation<Principal>> violations = validator.validate(director);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return principalRepository.save(director);
    }

    @Override
    public Principal update(Long directorId, Principal director) {
        Set<ConstraintViolation<Principal>> violations = validator.validate(director);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return principalRepository.findById(directorId).map(data ->
                principalRepository.save(
                        data.withSpecialty(director.getSpecialty())
                                .withExperienceYears(director.getExperienceYears())
                                .withSalary(director.getSalary())
                                .withUserId(director.getUserId()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, directorId));
    }

    @Override
    public ResponseEntity<?> delete(Long directorId) {
        return principalRepository.findById(directorId).map(data -> {
            principalRepository.delete(data);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, directorId));
    }
}

package com.totemcrew.directors.service;

import com.totemcrew.directors.domain.model.entity.Director;
import com.totemcrew.directors.domain.persistence.DirectorRepository;
import com.totemcrew.directors.domain.service.DirectorService;
import com.totemcrew.shared.exception.ResourceNotFoundException;
import com.totemcrew.shared.exception.ResourceValidationException;
import com.totemcrew.shared.mapping.EnhancedModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class DirectorServiceImpl implements DirectorService {

    private static final Logger logger = LoggerFactory.getLogger(DirectorServiceImpl.class);

    private static final String ENTITY = "Director";
    @Autowired
    private DirectorRepository directorRepository;
    @Autowired
    private Validator validator;

    @Autowired
    EnhancedModelMapper mapper;

    /*public DirectorServiceImpl(DirectorRepository directorRepository, Validator validator) {
        this.directorRepository = directorRepository;
        this.validator = validator;
    }*/

    @Override
    public List<Director> getAll() {
        return directorRepository.findAll();
    }

    @Override
    public Page<Director> getAll(Pageable pageable) {
        return directorRepository.findAll(pageable);
    }

    @Override
    public Director getById(Long directorId) {
        return directorRepository.findById(directorId)
                .orElseThrow( () -> new ResourceNotFoundException(ENTITY, directorId));
    }

    @Override
    public Director create(Director director) {
        Set<ConstraintViolation<Director>> violations = validator.validate(director);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        var existingEmail = directorRepository.existsByEmail(director.getEmail());
        if(existingEmail) {
            throw new ResourceValidationException("email is already taken");
        }

        return directorRepository.save(director);
    }

    @Override
    public Director update(Long directorId, Director director) {
        Set<ConstraintViolation<Director>> violations = validator.validate(director);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return directorRepository.findById(directorId).map( data ->
                directorRepository.save(
                        data.withFirst_name(director.getFirst_name())
                                .withLast_name(director.getLast_name())
                                .withAge(director.getAge())
                                .withEmail(director.getEmail())
                                .withPhone(director.getPhone()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, directorId));
    }

    @Override
    public ResponseEntity<?> delete(Long directorId) {
        return directorRepository.findById(directorId).map(data -> {
            directorRepository.delete(data);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, directorId));
    }
}

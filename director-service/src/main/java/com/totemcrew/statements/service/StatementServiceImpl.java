package com.totemcrew.statements.service;

import com.totemcrew.directors.domain.persistence.PrincipalRepository;
import com.totemcrew.shared.exception.ResourceNotFoundException;
import com.totemcrew.shared.exception.ResourceValidationException;
import com.totemcrew.statements.domain.model.entity.Statement;
import com.totemcrew.statements.domain.persistence.StatementRepository;
import com.totemcrew.statements.domain.service.StatementService;
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
public class StatementServiceImpl implements StatementService {

    private static final String ENTITY = "Statement";

    private final StatementRepository statementRepository;

    private final PrincipalRepository principalRepository;

    private final Validator validator;

    public StatementServiceImpl(StatementRepository announcementRepository, PrincipalRepository directorRepository, Validator validator) {
        super();
        this.statementRepository = announcementRepository;
        this.principalRepository = directorRepository;
        this.validator = validator;
    }

    @Override
    public List<Statement> getAllByPrincipalId(Long directorId) {
        var existingDirector = principalRepository.findById(directorId);

        if(existingDirector.isEmpty())
            throw new ResourceNotFoundException("Director", directorId);

        return statementRepository.findByPrincipalId(directorId);
    }

    @Override
    public Page<Statement> getAllByPrincipalId(Long directorId, Pageable pageable) {
        return statementRepository.findByPrincipalId(directorId, pageable);
    }

    @Override
    public Statement create(Long directorId, Statement request) {
        Set<ConstraintViolation<Statement>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return principalRepository.findById(directorId).map(director -> {
            request.setPrincipal(director);
            return statementRepository.save(request);
        }).orElseThrow(() -> new ResourceNotFoundException("Principal", directorId));
    }

    @Override
    public Statement update(Long announcementId, Statement request) {
        Set<ConstraintViolation<Statement>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return statementRepository.findById(announcementId).map(announcement ->
                        statementRepository.save(
                        announcement.withTitle(request.getTitle())
                                .withDescription(request.getDescription()))
                ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, announcementId));
    }

    @Override
    public ResponseEntity<?> delete(Long announcementId) {
        return statementRepository.findById(announcementId).map(announcement -> {
            statementRepository.delete(announcement);
                return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, announcementId));
    }
}

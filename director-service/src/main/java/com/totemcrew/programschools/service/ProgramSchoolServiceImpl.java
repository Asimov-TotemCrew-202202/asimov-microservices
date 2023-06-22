package com.totemcrew.programschools.service;

import com.totemcrew.directors.domain.persistence.PrincipalRepository;
import com.totemcrew.programschools.domain.model.entity.ProgramSchool;
import com.totemcrew.programschools.domain.presistence.ProgramSchoolRepository;
import com.totemcrew.programschools.domain.service.ProgramSchoolService;
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
public class ProgramSchoolServiceImpl implements ProgramSchoolService {

    private static final String ENTITY = "Program School";
    private final ProgramSchoolRepository programSchoolRepository;
    private final PrincipalRepository principalRepository;
    private final Validator validator;

    public ProgramSchoolServiceImpl(ProgramSchoolRepository programSchoolRepository, PrincipalRepository principalRepository, Validator validator) {
        this.programSchoolRepository = programSchoolRepository;
        this.principalRepository = principalRepository;
        this.validator = validator;
    }

    @Override
    public List<ProgramSchool> getAllByPrincipalId(Long principalId) {
        var existingDirector = principalRepository.findById(principalId);

        if(existingDirector.isEmpty())
            throw new ResourceNotFoundException("Director", principalId);

        return programSchoolRepository.findByPrincipalId(principalId);
    }

    @Override
    public Page<ProgramSchool> getAllByPrincipalId(Long principalId, Pageable pageable) {
        return programSchoolRepository.findByPrincipalId(principalId, pageable);
    }

    @Override
    public ProgramSchool create(Long principalId, ProgramSchool request) {
        Set<ConstraintViolation<ProgramSchool>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return principalRepository.findById(principalId).map(director -> {
            request.setPrincipal(director);
            return programSchoolRepository.save(request);
        }).orElseThrow(() -> new ResourceNotFoundException("Principal", principalId));
    }

    @Override
    public ProgramSchool update(Long programSchoolId, ProgramSchool request) {
        Set<ConstraintViolation<ProgramSchool>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return programSchoolRepository.findById(programSchoolId).map(ps ->
                programSchoolRepository.save(
                        ps.withTitle(request.getTitle()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, programSchoolId));
    }

    @Override
    public ResponseEntity<?> delete(Long programSchoolId) {
        return programSchoolRepository.findById(programSchoolId).map(ps -> {
            programSchoolRepository.delete(ps);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, programSchoolId));
    }
}

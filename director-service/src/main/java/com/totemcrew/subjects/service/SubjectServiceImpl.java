package com.totemcrew.subjects.service;

import com.totemcrew.directors.domain.persistence.PrincipalRepository;
import com.totemcrew.shared.exception.ResourceNotFoundException;
import com.totemcrew.shared.exception.ResourceValidationException;
import com.totemcrew.shared.mapping.EnhancedModelMapper;
import com.totemcrew.subjects.domain.model.entity.Subject;
import com.totemcrew.subjects.domain.persistence.SubjectRepository;
import com.totemcrew.subjects.domain.service.SubjectService;
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
public class SubjectServiceImpl implements SubjectService {
    private static final String ENTITY = "Subject";
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private PrincipalRepository principalRepository;
    @Autowired
    private Validator validator;

    @Autowired
    EnhancedModelMapper mapper;

    @Override
    public List<Subject> getAll() {
        return subjectRepository.findAll();
    }

    @Override
    public Page<Subject> getAll(Pageable pageable) {
        return subjectRepository.findAll(pageable);
    }

    @Override
    public List<Subject> getAllByPrincipalId(Long principalId) {
        var existingPrincipal = principalRepository.findById(principalId);
        if(existingPrincipal.isEmpty()) {
            throw new ResourceValidationException("principal not register");
        }

        return subjectRepository.findByPrincipalId(principalId);
    }

    @Override
    public Subject getById(Long subjectId) {
        return subjectRepository.findById(subjectId)
                .orElseThrow( () -> new ResourceNotFoundException(ENTITY, subjectId));
    }


    @Override
    public Subject create(Long principalId, Subject subject) {
        Set<ConstraintViolation<Subject>> violations = validator.validate(subject);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        var existingPrincipal = principalRepository.findById(principalId);
        if(existingPrincipal.isEmpty()) {
            throw new ResourceValidationException("principal not register");
        }

        subject.setPrincipal(existingPrincipal.get());
        return subjectRepository.save(subject);
    }

    @Override
    public Subject update(Long subjectId, Subject subject) {
        Set<ConstraintViolation<Subject>> violations = validator.validate(subject);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return subjectRepository.findById(subjectId).map( data ->
                subjectRepository.save(
                        data.withSchool_year(subject.getSchool_year())
                                .withGrade_id(subject.getGrade_id())
                                .withCourse_id(subject.getCourse_id()))
                                .withTeacher_id(subject.getTeacher_id())
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, subjectId));
    }

    @Override
    public ResponseEntity<?> delete(Long subjectId) {
        return subjectRepository.findById(subjectId).map(data -> {
            subjectRepository.delete(data);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, subjectId));
    }
}

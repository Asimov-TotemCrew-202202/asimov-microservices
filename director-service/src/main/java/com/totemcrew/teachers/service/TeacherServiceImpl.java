package com.totemcrew.teachers.service;

import com.totemcrew.directors.domain.persistence.PrincipalRepository;
import com.totemcrew.shared.exception.ResourceNotFoundException;
import com.totemcrew.shared.exception.ResourceValidationException;
import com.totemcrew.shared.mapping.EnhancedModelMapper;
import com.totemcrew.teachers.domain.model.entity.Teacher;
import com.totemcrew.teachers.domain.persistence.TeacherRepository;
import com.totemcrew.teachers.domain.service.TeacherService;
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
public class TeacherServiceImpl implements TeacherService {

    private static final String ENTITY = "Teacher";
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private PrincipalRepository principalRepository;
    @Autowired
    private Validator validator;

    @Autowired
    EnhancedModelMapper mapper;

    @Override
    public List<Teacher> getAll() {
        return teacherRepository.findAll();
    }

    @Override
    public Page<Teacher> getAll(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    @Override
    public List<Teacher> getAllByPrincipalId(Long principalId) {
        var existingPrincipal = principalRepository.findById(principalId);
        if(existingPrincipal.isEmpty()) {
            throw new ResourceValidationException("principal not register");
        }

        return teacherRepository.findByPrincipalId(principalId);
    }

    @Override
    public Teacher getById(Long teacherId) {
        return teacherRepository.findById(teacherId)
                .orElseThrow( () -> new ResourceNotFoundException(ENTITY, teacherId));
    }

    @Override
    public Teacher create(Long principalId, Teacher teacher) {
        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        var existingPrincipal = principalRepository.findById(principalId);
        if(existingPrincipal.isEmpty()) {
            throw new ResourceValidationException("principal not register");
        }

        teacher.setPrincipal(existingPrincipal.get());
        return teacherRepository.save(teacher);
    }

    @Override
    public Teacher update(Long teacherId, Teacher teacher) {
        Set<ConstraintViolation<Teacher>> violations = validator.validate(teacher);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return teacherRepository.findById(teacherId).map( data ->
                teacherRepository.save(
                        data.withSpeciality(teacher.getSpeciality())
                                .withSalary(teacher.getSalary())
                                .withUserId(teacher.getUserId()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, teacherId));
    }

    @Override
    public ResponseEntity<?> delete(Long teacherId) {
        return teacherRepository.findById(teacherId).map(data -> {
            teacherRepository.delete(data);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, teacherId));
    }
}

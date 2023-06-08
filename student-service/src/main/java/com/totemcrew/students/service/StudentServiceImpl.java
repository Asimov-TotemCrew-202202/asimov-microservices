package com.totemcrew.students.service;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.totemcrew.sections.domain.persistence.SectionRepository;
import com.totemcrew.shared.exception.ResourceNotFoundException;
import com.totemcrew.shared.exception.ResourceValidationException;
import com.totemcrew.students.domain.model.entity.Student;
import com.totemcrew.students.domain.persistence.StudentRepository;
import com.totemcrew.students.domain.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private static final String ENTITY = "Student";

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private Validator validator;

    @Override
    public List<Student> getAllBySectionId(Long sectionId) {
        var existingSection = sectionRepository.findById(sectionId);
        if (existingSection.isEmpty())
            throw new ResourceNotFoundException("Section", sectionId);
        return studentRepository.findBySectionId(sectionId);
    }

    @Override
    public Student getById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, studentId));
    }

    @Override
    public Student create(Long sectionId, Student student) {
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        var existingUserId = studentRepository.findByUserId(student.getUserId());
        if (existingUserId != null)
            throw new ResourceValidationException("UserId must to be unique");

        return sectionRepository.findById(sectionId).map(section -> {
            student.setSection(section);
            return studentRepository.save(student);
        }).orElseThrow(() -> new ResourceNotFoundException("Section ", sectionId));
    }

    @Override
    public Student update(Long studentId, Student request) {
        Set<ConstraintViolation<Student>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return studentRepository.findById(studentId)
                .map(student -> studentRepository.save(student.withParentFullname(request.getParentFullname())
                        .withPhoneParent(request.getPhoneParent()).withSection(student.getSection())
                        .withUserId(student.getUserId())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, studentId));
    }

    @Override
    public ResponseEntity<?> delete(Long studentId) {
        return studentRepository.findById(studentId).map(student -> {
            studentRepository.delete(student);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, studentId));
    }

}

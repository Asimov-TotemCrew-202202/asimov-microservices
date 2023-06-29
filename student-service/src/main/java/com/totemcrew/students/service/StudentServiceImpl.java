package com.totemcrew.students.service;

import com.totemcrew.sections.domain.persistence.SectionRepository;
import com.totemcrew.shared.exception.ResourceNotFoundException;
import com.totemcrew.shared.exception.ResourceValidationException;
import com.totemcrew.students.domain.model.entity.Student;
import com.totemcrew.students.domain.persistence.StudentRepository;
import com.totemcrew.students.domain.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentService {

    private static final String ENTITY = "Student";

    private StudentRepository studentRepository;
    private SectionRepository sectionRepository;
    private Validator validator;

    public StudentServiceImpl(StudentRepository studentRepository, SectionRepository sectionRepository, Validator validator) {
        this.studentRepository = studentRepository;
        this.sectionRepository = sectionRepository;
        this.validator = validator;
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getAllBySectionId(Long sectionId) {
        return studentRepository.findBySectionId(sectionId);
    }

    @Override
    public Page<Student> getAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public Student getById(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow( () -> new ResourceNotFoundException(ENTITY, studentId));
    }

    @Override
    public Student getByUserId(Long userId) {
        return studentRepository.findByUserId(userId);
    }

    @Override
    public Student create(Student student) {
        Set<ConstraintViolation<Student>> violations = validator.validate(student);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        if (sectionRepository.findById(student.getSectionId()).isEmpty())
            throw new ResourceValidationException("Section not exist");

        return studentRepository.save(student);
    }

    @Override
    public Student update(Long gradeId, Student student) {
        return null;
    }

    @Override
    public ResponseEntity<?> delete(Long studentId) {
        return null;
    }
}

package com.totemcrew.students.domain.service;

import com.totemcrew.students.domain.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StudentService {
    List<Student> getAll();
    List<Student> getAllBySectionId(Long sectionId);
    Page<Student> getAll(Pageable pageable);
    Student getById(Long studentId);
    Student getByUserId(Long userId);
    Student create(Student student);
    Student update(Long gradeId, Student student);
    ResponseEntity<?> delete(Long studentId);
}

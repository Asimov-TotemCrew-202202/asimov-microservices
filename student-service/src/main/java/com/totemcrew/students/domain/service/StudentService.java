package com.totemcrew.students.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.totemcrew.students.domain.model.entity.Student;

public interface StudentService {
    List<Student> getAllBySectionId(Long sectionId);
    Student getById(Long studentId);
    
    Student create(Long sectionId, Student student);
    Student update(Long studentId, Student student);
    ResponseEntity<?> delete(Long studentId);
}

package com.totemcrew.grades.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.totemcrew.grades.domain.model.entity.Grade;

public interface GradeService {
    List<Grade> getAll();
    Page<Grade> getAll(Pageable pageable);
    Grade getById(Long gradeId);
    
    Grade create(Grade grade);
    Grade update(Long gradeId, Grade grade);
    ResponseEntity<?> delete(Long gradeId);
}

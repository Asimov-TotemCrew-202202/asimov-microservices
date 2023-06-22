package com.totemcrew.courses.domain.service;

import com.totemcrew.competences.domain.model.entity.Competence;
import com.totemcrew.courses.domain.model.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CourseService {
    List<Course> getAll();
    Page<Course> getAll(Pageable pageable);
    Course getById(Long courseId);
    Course create(Course course);
    Course update(Long courseId, Course request);
    ResponseEntity<?> delete(Long courseId);
    List<Competence> getAllCompetences_Course(Long courseId);
    void linkCompetencesToCourse(Long courseId, List<Long> competenceIds);
}

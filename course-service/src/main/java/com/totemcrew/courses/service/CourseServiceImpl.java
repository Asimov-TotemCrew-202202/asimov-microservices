package com.totemcrew.courses.service;

import com.totemcrew.competences.domain.model.entity.Competence;
import com.totemcrew.competences.domain.persistence.CompetenceRepository;
import com.totemcrew.courses.domain.model.entity.Course;
import com.totemcrew.courses.domain.persistence.CourseRepository;
import com.totemcrew.courses.domain.service.CourseService;
import com.totemcrew.shared.exception.ResourceNotFoundException;
import com.totemcrew.shared.exception.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class CourseServiceImpl implements CourseService {

    private static final String ENTITY = "Course";

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CompetenceRepository competenceRepository;

    @Autowired
    private Validator validator;


    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public Page<Course> getAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    public Course getById(Long courseId) {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, courseId));
    }

    @Override
    public Course create(Course course) {
        Set<ConstraintViolation<Course>> violations = validator.validate(course);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        var existingName = courseRepository.findByName(course.getName());
        if(existingName != null) {
            throw new ResourceValidationException("course is already registered");
        }

        return courseRepository.save(course);
    }

    @Override
    public Course update(Long courseId, Course request) {
        Set<ConstraintViolation<Course>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return courseRepository.findById(courseId).map(course ->
                courseRepository.save(
                        course.withName(request.getName())
                                .withDescription(request.getDescription()))
        ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, courseId));
    }

    @Override
    public ResponseEntity<?> delete(Long courseId) {
        return courseRepository.findById(courseId).map(course -> {
            courseRepository.delete(course);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, courseId));
    }

    @Override
    public List<Competence> getAllCompetences_Course(Long courseId) {
        return courseRepository.getAllCompetences_Course(courseId);
    }

    @Override
    public void linkCompetencesToCourse(Long courseId, List<Long> competenceIds) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + courseId));
        List<Competence> competences = competenceRepository.findAllById(competenceIds);
        course.setCompetences(competences);
        courseRepository.save(course);
    }
}

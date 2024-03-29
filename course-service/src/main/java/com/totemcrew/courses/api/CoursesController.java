package com.totemcrew.courses.api;

import com.totemcrew.competences.mapping.CompetenceMapper;
import com.totemcrew.competences.resources.CompetenceResource;
import com.totemcrew.courses.domain.service.CourseService;
import com.totemcrew.courses.mapping.CourseMapper;
import com.totemcrew.courses.resource.CourseResource;
import com.totemcrew.courses.resource.CreateCourseResource;
import com.totemcrew.courses.resource.UpdateCourseResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class CoursesController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseMapper mapper;

    @Autowired
    CompetenceMapper competenceMapper;

    @GetMapping("courses")
    public List<CourseResource> getAllCourses() {
        return mapper.modelListToResource(courseService.getAll());
    }

    @GetMapping("courses/{courseId}")
    public CourseResource getCourseById(@PathVariable("courseId") Long courseId) {
        return mapper.toResource(courseService.getById(courseId));
    }

    @PostMapping("courses")
    public CourseResource createCourse(@RequestBody CreateCourseResource request) {

        var course = courseService.create(mapper.toModel(request));
        courseService.linkCompetencesToCourse(course.getId(), request.getCompetenceIds());
        return mapper.toResource(course);
    }

    @PutMapping("courses/{courseId}")
    public CourseResource updateCourse(@PathVariable Long courseId, @RequestBody UpdateCourseResource request) {
        return mapper.toResource(courseService.update(courseId, mapper.toModel(request)));
    }

    @DeleteMapping("courses/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        return courseService.delete(courseId);
    }

    @GetMapping("courses/{id}/competences")
    public List<CompetenceResource> getAllCoursesByTeacherId(@PathVariable("id") Long id) {
        return competenceMapper.modelListToResource(courseService.getAllCompetences_Course(id));
    }
}

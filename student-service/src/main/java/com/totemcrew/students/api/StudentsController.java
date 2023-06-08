package com.totemcrew.students.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.totemcrew.students.domain.service.StudentService;
import com.totemcrew.students.mapping.StudentMapper;
import com.totemcrew.students.resource.CreateStudentResource;
import com.totemcrew.students.resource.StudentResource;
import com.totemcrew.students.resource.UpdateStudentResource;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class StudentsController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper mapper;

    @GetMapping("sections/{sectionId}/students")
    public List<StudentResource> getAllSections(@PathVariable Long sectionId) {
        return mapper.modelListToResource(studentService.getAllBySectionId(sectionId));
    }

    @GetMapping("students/{studentId}")
    public StudentResource getById(@PathVariable Long studentId) {
        return mapper.toResource(studentService.getById(studentId));
    }

    @PostMapping("sections/{sectionId}/students")
    public StudentResource createSection(@RequestBody CreateStudentResource request, @PathVariable Long sectionId) {
        return mapper.toResource(studentService.create(sectionId, mapper.toModel(request)));
    }

    @PutMapping("students/{studentId}")
    public StudentResource updateSection(@PathVariable Long studentId, @RequestBody UpdateStudentResource request) {
        return mapper.toResource(studentService.update(studentId, mapper.toModel(request)));
    }

    @DeleteMapping("students/{studentId}")
    public ResponseEntity<?> deleteSection(@PathVariable Long studentId) {
        return studentService.delete(studentId);
    }

}

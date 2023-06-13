package com.totemcrew.students.api;

import com.totemcrew.students.domain.service.StudentService;
import com.totemcrew.students.mapping.StudentMapper;
import com.totemcrew.students.resource.CreateStudentResource;
import com.totemcrew.students.resource.StudentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentsController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentMapper mapper;


    @GetMapping
    public List<StudentResource> getAllStudentsBySection(@RequestParam(required = false) Long sectionId) {
        return mapper.modelListToResource(studentService.getAllBySectionId(sectionId));
    }

    @GetMapping("{studentId}")
    public StudentResource getStudentById(@PathVariable("studentId") Long studentId) {
        return mapper.toResource(studentService.getById(studentId));
    }
    @PostMapping
    public StudentResource createStudent(@RequestBody CreateStudentResource request) {
        return mapper.toResource(studentService.create(mapper.toModel(request)));
    }
}

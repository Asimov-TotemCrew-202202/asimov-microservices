package com.totemcrew.teachers.api;

import com.totemcrew.teachers.domain.service.TeacherService;
import com.totemcrew.teachers.mapping.TeacherMapper;
import com.totemcrew.teachers.resource.CreateTeacherResource;
import com.totemcrew.teachers.resource.TeacherResource;
import com.totemcrew.teachers.resource.UpdateTeacherResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TeachersController {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherMapper mapper;

    @GetMapping("teachers")
    public List<TeacherResource> getAllTeachers() { return mapper.modelListToResource(teacherService.getAll()); }

    @GetMapping("principals/{principalId}/teachers")
    public List<TeacherResource> getAllTeachersByPrincipal(@PathVariable Long principalId) {
        return mapper.modelListToResource(teacherService.getAllByPrincipalId(principalId));
    }

    @GetMapping("teachers/{teacherId}")
    public TeacherResource getTeacherById(@PathVariable("teacherId") Long teacherId) {
        return mapper.toResource(teacherService.getById(teacherId));
    }

    @PostMapping("principals/{principalId}/teachers")
    public TeacherResource createUser(@PathVariable Long principalId, @RequestBody CreateTeacherResource request) {
        return mapper.toResource(teacherService.create(principalId, mapper.toModel(request)));
    }

    @PutMapping("teachers{teacherId}")
    public TeacherResource updateTeacher(@PathVariable Long teacherId, @RequestBody UpdateTeacherResource request) {
        return mapper.toResource(teacherService.update(teacherId, mapper.toModel(request)));
    }

    @DeleteMapping("teachers{teacherId}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long teacherId) {
        return teacherService.delete(teacherId);
    }
}

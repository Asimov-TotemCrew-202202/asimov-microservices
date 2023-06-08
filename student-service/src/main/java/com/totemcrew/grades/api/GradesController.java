package com.totemcrew.grades.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.totemcrew.grades.domain.service.GradeService;
import com.totemcrew.grades.mapping.GradeMapper;
import com.totemcrew.grades.resource.CreateGradeResource;
import com.totemcrew.grades.resource.GradeResource;
import com.totemcrew.grades.resource.UpdateGradeResource;

import java.util.List;

@RestController
@RequestMapping("api/v1/grades")
public class GradesController {
    
    @Autowired
    private GradeService gradeService;

    @Autowired
    private GradeMapper mapper;

    @GetMapping
    public List<GradeResource> getAllGrades() { return mapper.modelListToResource(gradeService.getAll()); }

    @GetMapping("{gradeId}")
    public GradeResource getGradeById(@PathVariable("gradeId") Long gradeId) {
        return mapper.toResource(gradeService.getById(gradeId));
    }

    @PostMapping("")
    public GradeResource createGrade(@RequestBody CreateGradeResource request) {
        return mapper.toResource(gradeService.create(mapper.toModel(request)));
    }

    @PutMapping("{gradeId}")
    public GradeResource updateGrade(@PathVariable Long gradeId, @RequestBody UpdateGradeResource request) {
        return mapper.toResource(gradeService.update(gradeId, mapper.toModel(request)));
    }

    @DeleteMapping("{gradeId}")
    public ResponseEntity<?> deleteGrade(@PathVariable Long gradeId) {
        return gradeService.delete(gradeId);
    }
}

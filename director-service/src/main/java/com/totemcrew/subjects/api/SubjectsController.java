package com.totemcrew.subjects.api;

import com.totemcrew.subjects.domain.service.SubjectService;
import com.totemcrew.subjects.mapping.SubjectMapper;
import com.totemcrew.subjects.resource.CreateSubjectResource;
import com.totemcrew.subjects.resource.SubjectResource;
import com.totemcrew.subjects.resource.UpdateSubjectResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class SubjectsController {
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private SubjectMapper mapper;

    @GetMapping("subjects")
    public List<SubjectResource> getAllSubjects(){ return mapper.modelListToResource(subjectService.getAll()); }

    @GetMapping("principals/{principalId}/subjects")
    public List<SubjectResource> getAllSubjectsByPrincipal(@PathVariable Long principalId) {
        return mapper.modelListToResource(subjectService.getAllByPrincipalId(principalId));
    }

    @GetMapping("subjects/{subjectId}")
    public SubjectResource getSubjectById(@PathVariable("subjectId") Long subjectId) {
        return mapper.toResource(subjectService.getById(subjectId));
    }

    @PostMapping("principals/{principalId}/subjects")
    public SubjectResource createSubject(@PathVariable Long principalId, @RequestBody CreateSubjectResource request) {
        return mapper.toResource(subjectService.create(principalId, mapper.toModel(request)));
    }

    @PutMapping("subjects{subjectId}")
    public SubjectResource updateSubject(@PathVariable Long subjectId, @RequestBody UpdateSubjectResource request) {
        return mapper.toResource(subjectService.update(subjectId, mapper.toModel(request)));
    }

    @DeleteMapping("subjects{subjectId}")
    public ResponseEntity<?> deleteSubject(@PathVariable Long subjectId) {
        return subjectService.delete(subjectId);
    }

}

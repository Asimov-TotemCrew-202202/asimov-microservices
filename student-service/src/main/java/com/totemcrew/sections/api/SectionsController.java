package com.totemcrew.sections.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.totemcrew.sections.domain.service.SectionService;
import com.totemcrew.sections.mapping.SectionMapper;
import com.totemcrew.sections.resource.CreateSectionResource;
import com.totemcrew.sections.resource.SectionResource;
import com.totemcrew.sections.resource.UpdateSectionResource;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class SectionsController {

    @Autowired
    private SectionService sectionService;

    @Autowired
    private SectionMapper mapper;

    @GetMapping("grades/{gradeId}/sections")
    public List<SectionResource> getAllSections(@PathVariable Long gradeId) {
        return mapper.modelListToResource(sectionService.getAllByGradeId(gradeId));
    }

    @GetMapping("sections/{sectionId}")
    public SectionResource getById(@PathVariable Long sectionId) {
        return mapper.toResource(sectionService.getById(sectionId));
    }

    @PostMapping("grades/{gradeId}/sections")
    public SectionResource createSection(@RequestBody CreateSectionResource request, @PathVariable Long gradeId) {
        return mapper.toResource(sectionService.create(gradeId, mapper.toModel(request)));
    }

    @PutMapping("sections/{sectionId}")
    public SectionResource updateSection(@PathVariable Long sectionId, @RequestBody UpdateSectionResource request) {
        return mapper.toResource(sectionService.update(sectionId, mapper.toModel(request)));
    }

    @DeleteMapping("sections/{sectionId}")
    public ResponseEntity<?> deleteSection(@PathVariable Long sectionId) {
        return sectionService.delete(sectionId);
    }
}

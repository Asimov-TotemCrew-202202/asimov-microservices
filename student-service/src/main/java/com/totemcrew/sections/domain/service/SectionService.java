package com.totemcrew.sections.domain.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.totemcrew.sections.domain.model.entity.Section;

public interface SectionService {
    List<Section> getAllByGradeId(Long gradeId);
    Page<Section> getAllByGradeId(Long gradeId, Pageable pageable);
    Section getById(Long sectionId);
    
    Section create(Long gradeId, Section section);
    Section update(Long sectionId, Section section);
    ResponseEntity<?> delete(Long sectionId);
}

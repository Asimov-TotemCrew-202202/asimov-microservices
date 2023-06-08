package com.totemcrew.sections.domain.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.totemcrew.sections.domain.model.entity.Section;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {
    Section findByName(String name);
    Section findByGradeIdAndName(Long gradeId, String name);
    List<Section> findByGradeId(Long gradeId);
    Page<Section> findByGradeId(Long gradeId, Pageable pageable);
}

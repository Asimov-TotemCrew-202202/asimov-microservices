package com.totemcrew.competences.domain.persistence;

import com.totemcrew.competences.domain.model.entity.Competence;
import com.totemcrew.courses.domain.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {

}

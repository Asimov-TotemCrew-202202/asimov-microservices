package com.totemcrew.competences.domain.persistence;

import com.totemcrew.competences.domain.model.entity.Competence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetenceRepository extends JpaRepository<Competence, Long> {

}

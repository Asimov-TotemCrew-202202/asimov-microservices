package com.totemcrew.grades.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.totemcrew.grades.domain.model.entity.Grade;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Grade findByName(String name);
}

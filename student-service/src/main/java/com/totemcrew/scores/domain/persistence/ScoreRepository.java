package com.totemcrew.scores.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.totemcrew.scores.domain.model.Score;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {
    Score findByStudentIdAndExamId(Long studentId, Long examId);
}

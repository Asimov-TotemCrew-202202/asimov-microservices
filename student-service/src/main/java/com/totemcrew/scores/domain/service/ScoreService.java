package com.totemcrew.scores.domain.service;

import java.util.List;

import com.totemcrew.scores.domain.model.Score;

public interface ScoreService {
    Score getById(Long id);
    Score create(Score score, Long studentId, Long examId);
    Score getByStudentIdAndExamId(Long studentId, Long examId);
    List<Score> getByStudentId(Long studentId);
    Score update (Score score, Long id);
}

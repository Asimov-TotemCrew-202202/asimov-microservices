package com.totemcrew.scores.domain.service;

import com.totemcrew.scores.domain.model.Score;

public interface ScoreService {
    Score getById(Long id);
    Score create(Score score, Long studentId, Long examId);
    Score getByStudentIdAndExamId(Long studentId, Long examId);
    Score update (Score score, Long id);
}

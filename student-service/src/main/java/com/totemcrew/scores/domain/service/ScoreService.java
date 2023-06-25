package com.totemcrew.scores.domain.service;

import com.totemcrew.scores.domain.model.Score;

public interface ScoreService {
    Score create(Score score, Long studentId, Long examId);
}

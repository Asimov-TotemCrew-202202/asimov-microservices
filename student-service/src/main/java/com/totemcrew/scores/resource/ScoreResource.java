package com.totemcrew.scores.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScoreResource {
    private Long id;
    private int points;
    private int numberQuestions;
    private Long studentId;
    private Long examId;
}

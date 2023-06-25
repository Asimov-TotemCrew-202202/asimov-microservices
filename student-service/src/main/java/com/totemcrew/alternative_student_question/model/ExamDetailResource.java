package com.totemcrew.alternative_student_question.model;

import java.util.List;

import lombok.*;

@Data
public class ExamDetailResource {
    private Long id;
    private String question;
    private List<String> options;
    private int correctOptionOrder;
    private Long examId;
}

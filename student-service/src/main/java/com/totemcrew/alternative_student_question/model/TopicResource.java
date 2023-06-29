package com.totemcrew.alternative_student_question.model;

import lombok.Data;

@Data
public class TopicResource {
    private Long id;
    private String title;
    private String description;
    private String file;
    private Boolean status;
}

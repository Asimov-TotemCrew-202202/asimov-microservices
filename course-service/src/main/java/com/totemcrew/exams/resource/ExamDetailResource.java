package com.totemcrew.exams.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExamDetailResource {

    private Long id;
    private String question;
    private List<String> options;
    private int correctOptionOrder;
}

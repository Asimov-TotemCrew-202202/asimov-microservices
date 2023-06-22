package com.totemcrew.exams.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class CreateExamDetailResource {

    @NotNull
    @NotBlank
    @Size(max = 120)
    private String question;
    private List<String> options;
    private int correctOptionOrder;
}

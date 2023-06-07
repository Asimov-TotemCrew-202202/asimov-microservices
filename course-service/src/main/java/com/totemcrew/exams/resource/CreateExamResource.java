package com.totemcrew.exams.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateExamResource {

    @NotNull
    @NotBlank
    @Size(max = 120)
    private String title;

    @NotNull
    @NotBlank
    private String description;
}

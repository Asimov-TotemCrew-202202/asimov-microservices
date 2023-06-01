package com.totemcrew.teachers.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateTeacherResource {

    @NotNull
    @NotBlank
    @Size(max = 45)
    private String speciality;

    private double salary;

    private Long userId;
}

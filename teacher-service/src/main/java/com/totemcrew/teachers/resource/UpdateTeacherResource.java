package com.totemcrew.teachers.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateTeacherResource {

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String first_name;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String last_name;

    private int age;

    @NotNull
    @NotBlank
    @Size(max = 40)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String phone;
}

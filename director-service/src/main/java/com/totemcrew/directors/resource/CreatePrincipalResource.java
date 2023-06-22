package com.totemcrew.directors.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreatePrincipalResource {

    @NotNull
    @NotBlank
    @Size(max = 45)
    private String specialty;

    @NotNull
    private double experienceYears;

    private double salary;

    private Long userId;
}

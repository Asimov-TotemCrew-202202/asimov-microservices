package com.totemcrew.programschools.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateProgramSchoolResource {

    @NotNull
    @NotBlank
    @Size(max = 120)
    private String title;
}

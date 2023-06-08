package com.totemcrew.sections.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateSectionResource {
    @NotNull
    @NotBlank
    @Size(max = 45)
    private String name;
}

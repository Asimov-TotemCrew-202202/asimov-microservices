package com.totemcrew.courses.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class CreateCourseResource {

    @NotNull
    @NotBlank
    @Size(max = 120)
    private String name;

    @NotNull
    @NotBlank
    private String description;

    private List<Long> competenceIds;
}

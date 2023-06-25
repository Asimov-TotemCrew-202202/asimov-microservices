package com.totemcrew.subjects.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
public class CreateSubjectResource {

    @NotNull
    @NotBlank
    private String school_year;

    @NotNull
    private Long teacher_id ;

    @NotNull
    private Long course_id;

    @NotNull
    private Long grade_id;

}

package com.totemcrew.subjects.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
public class SubjectResource {
    private Long id;
    private String school_year;
    private Long teacher_id ;
    private Long course_id;
    private Long grade_id;
}

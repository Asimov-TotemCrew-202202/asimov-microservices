package com.totemcrew.courses.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseResource {

    private Long id;

    private String name;

    private String description;

    private Boolean state;
}

package com.totemcrew.students.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentResource {

    private Long id;
    private String parentFullName;
    private String phoneParent;
    private Long userId;
    private Long sectionId;
}

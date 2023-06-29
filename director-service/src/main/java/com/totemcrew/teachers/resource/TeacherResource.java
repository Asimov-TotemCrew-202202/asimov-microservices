package com.totemcrew.teachers.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherResource {

    private Long id;
    private String speciality;
    private double salary;
    private Long userId;
    private Long principalId;
}

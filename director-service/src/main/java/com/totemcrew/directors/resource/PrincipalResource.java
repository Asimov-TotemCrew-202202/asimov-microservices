package com.totemcrew.directors.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrincipalResource {

    private Long id;
    private String specialty;
    private double experienceYears;
    private double salary;
    private Long userId;
}

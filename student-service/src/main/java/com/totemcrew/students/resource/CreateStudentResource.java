package com.totemcrew.students.resource;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CreateStudentResource {
    @NotNull
    @NotBlank
    @Size(max = 60)
    private String parentFullname;

    @NotNull
    @NotBlank
    @Size(max = 45)
    private String phoneParent;

    @NotNull
    @Column(unique = true)
    private Long userId;
}

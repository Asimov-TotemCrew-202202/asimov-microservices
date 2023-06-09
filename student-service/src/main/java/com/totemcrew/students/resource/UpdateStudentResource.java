package com.totemcrew.students.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UpdateStudentResource {

    @NotNull
    @NotBlank
    @Size(max = 60)
    private String parentFullName;

    @NotNull
    @NotBlank
    @Size(max = 45)
    private String phoneParent;

    private Long userId;
    private Long sectionId;
}

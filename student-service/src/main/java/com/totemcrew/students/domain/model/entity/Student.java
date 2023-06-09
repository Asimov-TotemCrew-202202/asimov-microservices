package com.totemcrew.students.domain.model.entity;

import com.totemcrew.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name = "students")
public class Student extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

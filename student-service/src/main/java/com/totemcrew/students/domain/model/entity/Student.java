package com.totemcrew.students.domain.model.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.totemcrew.sections.domain.model.entity.Section;
import com.totemcrew.shared.domain.model.AuditModel;

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
    private String parentFullname;

    @NotNull
    @NotBlank
    @Size(max = 45)
    private String phoneParent;

    @NotNull
    @Column(unique = true)
    private Long userId;

    //relationship
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "section_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Section section;
}

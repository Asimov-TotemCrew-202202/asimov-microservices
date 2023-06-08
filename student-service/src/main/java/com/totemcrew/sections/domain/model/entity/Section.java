package com.totemcrew.sections.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.totemcrew.grades.domain.model.entity.Grade;
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
@Table(name = "sections")
public class Section extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 45)
    private String name;

    //relationships
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "grade_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Grade grade;
}

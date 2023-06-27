package com.totemcrew.subjects.domain.model.entity;

import com.totemcrew.directors.domain.model.entity.Principal;
import com.totemcrew.shared.domain.model.AuditModel;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name = "subjects")
public class Subject extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    private String school_year;

    @NotNull
    private Long teacher_id ;

    @NotNull
    private Long course_id;

    @NotNull
    private Long grade_id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "principal_id", nullable = false)
    private Principal principal;
}

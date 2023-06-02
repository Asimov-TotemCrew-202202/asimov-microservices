package com.totemcrew.competences.domain.model.entity;

import com.totemcrew.courses.domain.model.entity.Course;
import com.totemcrew.shared.domain.model.AuditModel;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name = "competences")
public class Competence extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 120)
    private String name;

    @NotNull
    @NotBlank
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    @ManyToMany(mappedBy = "competences")
    private List<Course> courses;


}

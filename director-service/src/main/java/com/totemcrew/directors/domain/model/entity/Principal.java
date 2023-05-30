package com.totemcrew.directors.domain.model.entity;

import com.totemcrew.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "principals")
public class Principal extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 45)
    private String specialty;

    @NotNull
    private double experienceYears;

    private double salary;

    private Long principalId;

    //relation with announcements
    @OneToMany(mappedBy = "principal", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Statement> statements;

}

package com.totemcrew.directors.domain.model.entity;

import com.totemcrew.announcements.domain.model.entity.Announcement;
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
@Table(name = "directors")
public class Director extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String first_name;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String last_name;

    private int age;

    @NotNull
    @NotBlank
    @Size(max = 40)
    private String email;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String phone;

    //relation with announcements
    @OneToMany(mappedBy = "director", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<Announcement> announcements;

}

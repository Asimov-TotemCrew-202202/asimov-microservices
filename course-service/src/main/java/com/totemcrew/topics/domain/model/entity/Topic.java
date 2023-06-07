package com.totemcrew.topics.domain.model.entity;

import com.totemcrew.courses.domain.model.entity.Course;
import com.totemcrew.exams.domain.model.entity.Exam;
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
@Table(name = "topics")
public class Topic extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 30)
    private String title;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String description;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String file;

    //relation with courses
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @OneToOne(mappedBy = "topic")
    private Exam exam;
}

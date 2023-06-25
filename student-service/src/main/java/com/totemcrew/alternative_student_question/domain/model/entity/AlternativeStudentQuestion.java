package com.totemcrew.alternative_student_question.domain.model.entity;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.totemcrew.alternative_student_question.model.ExamDetailResource;
import com.totemcrew.shared.domain.model.AuditModel;
import com.totemcrew.students.domain.model.entity.Student;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name = "alternative_student_question")
public class AlternativeStudentQuestion extends AuditModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private int checkedAlternative;

    @NotNull
    private Boolean isCorrect;

    //relationships
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Student student;

    private Long examDetailId;
}

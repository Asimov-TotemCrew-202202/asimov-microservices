package com.totemcrew.exams.domain.model.entity;

import com.totemcrew.shared.domain.model.AuditModel;
import com.totemcrew.topics.domain.model.entity.Topic;
import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@With
@Table(name = "exams")
public class Exam extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany( mappedBy = "exam", fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private List<ExamDetail> examDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "topic_id", referencedColumnName = "id")
    private Topic topic;
}

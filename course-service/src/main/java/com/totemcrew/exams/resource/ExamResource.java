package com.totemcrew.exams.resource;

import com.totemcrew.exams.domain.model.entity.ExamDetail;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ElementCollection;
import java.util.List;

@Getter
@Setter
public class ExamResource {

    private Long id;
    List<CreateExamDetailResource> examDetailResources;
}

package com.totemcrew.exams.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExamResource {
    private Long id;
    List<ExamDetailResource> examDetailResources;
}

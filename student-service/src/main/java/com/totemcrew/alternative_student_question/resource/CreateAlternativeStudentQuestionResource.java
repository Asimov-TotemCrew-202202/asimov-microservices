package com.totemcrew.alternative_student_question.resource;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAlternativeStudentQuestionResource {
    @NotNull
    private int checkedAlternative;
}

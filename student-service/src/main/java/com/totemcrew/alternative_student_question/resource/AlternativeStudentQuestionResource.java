package com.totemcrew.alternative_student_question.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlternativeStudentQuestionResource {
    private Long id;
    private int checkedAlternative;
    private Boolean isCorrect;
    private Long studentId;
    private Long examDetailId;
    private Long examId;
}

package com.totemcrew.alternative_student_question.domain.service;

import com.totemcrew.alternative_student_question.domain.model.entity.AlternativeStudentQuestion;
import com.totemcrew.grades.domain.model.entity.Grade;

public interface AlternativeStudentQuestionService {
    AlternativeStudentQuestion getById(Long alternativeId);

    AlternativeStudentQuestion create(AlternativeStudentQuestion alternative, Long studentId, Long examDetailId);
}

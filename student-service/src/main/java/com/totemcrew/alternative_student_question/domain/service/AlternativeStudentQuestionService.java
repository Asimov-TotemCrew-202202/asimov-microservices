package com.totemcrew.alternative_student_question.domain.service;

import java.util.List;

import com.totemcrew.alternative_student_question.domain.model.entity.AlternativeStudentQuestion;

public interface AlternativeStudentQuestionService {
    AlternativeStudentQuestion getById(Long alternativeId);
    List<AlternativeStudentQuestion> getByStudentIdAndExamId(Long studentId, Long examId);

    AlternativeStudentQuestion create(AlternativeStudentQuestion alternative, Long studentId, Long examDetailId);
}

package com.totemcrew.exams.domain.service;

import com.totemcrew.exams.domain.model.entity.ExamDetail;

import java.util.List;

public interface ExamDetailService {

    List<ExamDetail> getAllByExamId(Long examId);
    ExamDetail getByIdAndExamId(Long id, Long examId);
    ExamDetail create(ExamDetail item, Long examId);
}

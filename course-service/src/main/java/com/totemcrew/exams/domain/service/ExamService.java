package com.totemcrew.exams.domain.service;

import com.totemcrew.exams.domain.model.entity.Exam;

import org.springframework.http.ResponseEntity;

public interface ExamService {

    Exam getById(Long examId);
    Exam getExamByTopicId(Long topicId);
    Exam create(Exam exam, Long topicId);
    Exam update(Exam exam, Long topicId);
    ResponseEntity<?> delete(Long topicId);
}

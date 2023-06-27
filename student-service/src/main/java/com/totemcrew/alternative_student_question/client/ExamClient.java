package com.totemcrew.alternative_student_question.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.totemcrew.alternative_student_question.model.ExamDetailResource;
import com.totemcrew.alternative_student_question.model.ExamTopicResource;

@FeignClient(name = "course-service", path = "/api/v1")
public interface ExamClient {
    @GetMapping("exams/{examId}") 
    public ExamTopicResource getExamById(@PathVariable Long examId);

    @GetMapping("exams/examDetail/{id}")
    public ExamDetailResource getExamDetailById(@PathVariable Long id);
}

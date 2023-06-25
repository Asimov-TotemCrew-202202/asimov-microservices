package com.totemcrew.alternative_student_question.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.totemcrew.alternative_student_question.model.ExamDetailResource;

@FeignClient(name = "course-service")
@RequestMapping("api/v1")
public interface ExamDetailClient {
    
    @GetMapping("exams/examDetail/{id}")
    public ExamDetailResource getExamDetailById(@PathVariable Long id);
}

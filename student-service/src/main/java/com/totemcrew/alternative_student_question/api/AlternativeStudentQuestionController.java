package com.totemcrew.alternative_student_question.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.totemcrew.alternative_student_question.domain.service.AlternativeStudentQuestionService;
import com.totemcrew.alternative_student_question.mapping.AlternativeStudentQuestionMapper;
import com.totemcrew.alternative_student_question.resource.AlternativeStudentQuestionResource;
import com.totemcrew.alternative_student_question.resource.CreateAlternativeStudentQuestionResource;

@RestController
@RequestMapping("api/v1")
public class AlternativeStudentQuestionController {
    
    @Autowired
    private AlternativeStudentQuestionService alternativeStudentQuestionService;

    @Autowired
    private AlternativeStudentQuestionMapper mapper;

    @GetMapping("alternatives/{id}")
    public AlternativeStudentQuestionResource getById(@PathVariable Long id) {
        return mapper.toResource(alternativeStudentQuestionService.getById(id));
    }

    @GetMapping("alternatives/students/{studentId}/exams/{examId}")
    public List<AlternativeStudentQuestionResource> getByStudentIdAndExamId(@PathVariable Long studentId, @PathVariable Long examId) {
        return mapper.modelListToResource(alternativeStudentQuestionService.getByStudentIdAndExamId(studentId, examId));
    }

    @PostMapping("alternatives/students/{studentId}/examDetail/{detailId}")
    public AlternativeStudentQuestionResource createSection(@RequestBody CreateAlternativeStudentQuestionResource request, @PathVariable Long studentId, @PathVariable Long detailId) {
        return mapper.toResource(alternativeStudentQuestionService.create(mapper.toModel(request), studentId, detailId));
    }
}

package com.totemcrew.exams.api;

import com.totemcrew.exams.domain.service.ExamService;
import com.totemcrew.exams.mapping.ExamMapper;
import com.totemcrew.exams.resource.CreateExamResource;
import com.totemcrew.exams.resource.ExamResource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ExamsController {

    private final ExamService examService;
    private final ExamMapper mapper;

    public ExamsController(ExamService examService, ExamMapper mapper) {
        this.examService = examService;
        this.mapper = mapper;
    }

    @GetMapping("topics/{topicId}/exams")
    public ExamResource getAllByTopicId(@PathVariable Long topicId) {
        return mapper.toResource(examService.getExamByTopicId(topicId));
    }

    @PostMapping("topics/{topicId}/exams")
    public ExamResource createExam(@RequestBody CreateExamResource request, @PathVariable Long topicId) {
        return mapper.toResource(examService.create(mapper.toModel(request),topicId));
    }
}

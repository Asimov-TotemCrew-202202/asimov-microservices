package com.totemcrew.exams.api;

import com.totemcrew.exams.domain.service.ExamDetailService;
import com.totemcrew.exams.domain.service.ExamService;
import com.totemcrew.exams.mapping.ExamDetailMapper;
import com.totemcrew.exams.mapping.ExamMapper;
import com.totemcrew.exams.resource.CreateExamResource;
import com.totemcrew.exams.resource.ExamDetailResource;
import com.totemcrew.exams.resource.ExamResource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class ExamsController {

    private final ExamService examService;
    private final ExamDetailService examDetailService;
    private final ExamMapper mapper;
    private final ExamDetailMapper examDetailMapper;

    public ExamsController(ExamService examService, ExamDetailService examDetailService, ExamMapper mapper, ExamDetailMapper examDetailMapper) {
        this.examService = examService;
        this.examDetailService = examDetailService;
        this.mapper = mapper;
        this.examDetailMapper = examDetailMapper;
    }

    @GetMapping("topics/{topicId}/exams")
    public ExamResource getExamByTopicId(@PathVariable Long topicId) {
        var exam = mapper.toResource(examService.getExamByTopicId(topicId));
        var examDetail = examDetailMapper.modelListToResource(examDetailService.getAllByExamId(exam.getId()));
        exam.setExamDetailResources(examDetail);
        return exam;
  
        //return mapper.toResource(examService.getExamByTopicId(topicId));
    }

    @GetMapping("exams/{examId}/examDetail/{id}")
    public ExamDetailResource getExamDetailByIdAndExamId(@PathVariable Long examId, @PathVariable Long id) {
        System.out.println("here " + id + " " + examId);
        var examDetail = examDetailMapper.modelToResource(examDetailService.getByIdAndExamId(id, examId));
        return examDetail;
    }

    // @GetMapping("topics/{topicId}/exams")
    // public ExamDetailResource getExamDetailByIdAndExamId(@PathVariable Long topicId) {
    //     System.out.println("here " + topicId);
    //     var examDetail = examDetailMapper.modelToResource(examDetailService.getByIdAndExamId(topicId, topicId));
    //     return examDetail;
    // }

    @PostMapping("topics/{topicId}/exams")
    public ExamResource createExam(@RequestBody CreateExamResource request, @PathVariable Long topicId) {
        var exam = mapper.toResource(examService.create(mapper.toModel(request),topicId));
        request.getExamDetailResources().forEach(data -> {
            examDetailService.create(examDetailMapper.toModel(data), exam.getId());
        });
        return exam;
    }
}

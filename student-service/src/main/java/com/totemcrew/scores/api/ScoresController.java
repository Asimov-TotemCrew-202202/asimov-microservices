package com.totemcrew.scores.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.totemcrew.scores.domain.service.ScoreService;
import com.totemcrew.scores.mapping.ScoreMapper;
import com.totemcrew.scores.resource.ScoreResource;


@RestController
@RequestMapping("api/v1")
public class ScoresController {
    
    @Autowired
    private ScoreService scoreService;

    @Autowired
    private ScoreMapper mapper;

    @GetMapping("scores/{id}")
    public ScoreResource getById(@PathVariable Long id) {
        return mapper.toResource(scoreService.getById(id));
    }

    @GetMapping("scores/students/{studentId}")
    public List<ScoreResource> getByStudentId(@PathVariable Long studentId) {
        return mapper.modelListToResource(scoreService.getByStudentId(studentId));
    }

    @GetMapping("scores/students/{studentId}/exams/{examId}")
    public ScoreResource getByStudentIdAndExamId(@PathVariable Long studentId, @PathVariable Long examId) {
        return mapper.toResource(scoreService.getByStudentIdAndExamId(studentId, examId));
    }
}

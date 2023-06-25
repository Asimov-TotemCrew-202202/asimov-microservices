package com.totemcrew.scores.service;

import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totemcrew.alternative_student_question.client.ExamDetailClient;
import com.totemcrew.scores.domain.model.Score;
import com.totemcrew.scores.domain.persistence.ScoreRepository;
import com.totemcrew.scores.domain.service.ScoreService;
import com.totemcrew.students.domain.persistence.StudentRepository;

@Service
public class ScoreServiceImpl implements ScoreService {

    private static final String ENTITY = "Score";

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private Validator validator;

    // @Autowired
    // ExamDetailClient examDetailClient;

    @Autowired
    StudentRepository studentRepository;

    @Override
    public Score create(Score score, Long studentId, Long examId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'create'");
    }    
}

package com.totemcrew.alternative_student_question.mapping;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;

import com.totemcrew.alternative_student_question.domain.model.entity.AlternativeStudentQuestion;
import com.totemcrew.alternative_student_question.resource.AlternativeStudentQuestionResource;
import com.totemcrew.alternative_student_question.resource.CreateAlternativeStudentQuestionResource;
import com.totemcrew.shared.mapping.EnhancedModelMapper;

import java.util.List;

public class AlternativeStudentQuestionMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public AlternativeStudentQuestionResource toResource(AlternativeStudentQuestion model) {
        return mapper.map(model, AlternativeStudentQuestionResource.class);
    }

    public List<AlternativeStudentQuestionResource> modelListToResource(List<AlternativeStudentQuestion> modelList) {
        return mapper.mapList(modelList, AlternativeStudentQuestionResource.class);
    }

    public AlternativeStudentQuestion toModel(CreateAlternativeStudentQuestionResource resource) {
        return mapper.map(resource, AlternativeStudentQuestion.class);
    }
}

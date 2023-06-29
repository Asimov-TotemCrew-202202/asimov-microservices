package com.totemcrew.exams.mapping;

import com.totemcrew.exams.domain.model.entity.ExamDetail;
import com.totemcrew.exams.resource.CreateExamDetailResource;
import com.totemcrew.exams.resource.ExamDetailResource;
import com.totemcrew.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ExamDetailMapper {
    @Autowired
    EnhancedModelMapper mapper;

    public ExamDetailResource toResource(ExamDetail model) {return mapper.map(model, ExamDetailResource.class);}

    public List<ExamDetailResource> modelListToResource(List<ExamDetail> modelList) {
        return mapper.mapList(modelList, ExamDetailResource.class);
    }

    public ExamDetailResource modelToResource(ExamDetail resource) {
        return mapper.map(resource, ExamDetailResource.class);
    }

    public List<CreateExamDetailResource> modelListToCreateResource(List<ExamDetail> modelList) {
        return mapper.mapList(modelList, CreateExamDetailResource.class);
    }

    public ExamDetail toModel(CreateExamDetailResource resource) {
        return mapper.map(resource, ExamDetail.class);
    }
}

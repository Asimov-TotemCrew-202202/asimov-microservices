package com.totemcrew.exams.mapping;

import com.totemcrew.exams.domain.model.entity.Exam;
import com.totemcrew.exams.resource.CreateExamResource;
import com.totemcrew.exams.resource.ExamResource;
import com.totemcrew.exams.resource.ExamTopicResource;
import com.totemcrew.exams.resource.UpdateExamResource;
import com.totemcrew.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ExamMapper {

    @Autowired
    EnhancedModelMapper mapper;

    public ExamResource toResource(Exam model) {
        return mapper.map(model, ExamResource.class);
    }

    public ExamTopicResource toExamTopicResource(Exam model) {
        return mapper.map(model, ExamTopicResource.class);
    }

    public Page<ExamResource> modelListToPage(List<Exam> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ExamResource.class), pageable, modelList.size());
    }

    public List<ExamResource> modelListToResource(List<Exam> modelList) {
        return mapper.mapList(modelList, ExamResource.class);
    }

    public Exam toModel(CreateExamResource resource) {
        return mapper.map(resource, Exam.class);
    }

    public Exam toModel(UpdateExamResource resource) {
        return mapper.map(resource, Exam.class);
    }
}

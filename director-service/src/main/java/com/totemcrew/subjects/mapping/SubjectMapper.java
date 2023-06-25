package com.totemcrew.subjects.mapping;

import com.totemcrew.shared.mapping.EnhancedModelMapper;
import com.totemcrew.subjects.domain.model.entity.Subject;
import com.totemcrew.subjects.resource.CreateSubjectResource;
import com.totemcrew.subjects.resource.SubjectResource;
import com.totemcrew.subjects.resource.UpdateSubjectResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class SubjectMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public SubjectResource toResource(Subject model) {
        return mapper.map(model, SubjectResource.class);
    }

    public Page<SubjectResource> modelListToPage(List<Subject> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, SubjectResource.class), pageable, modelList.size());
    }

    public List<SubjectResource> modelListToResource(List<Subject> modelList){
        return mapper.mapList(modelList, SubjectResource.class);
    }

    public Subject toModel(CreateSubjectResource resource) {
        return mapper.map(resource, Subject.class);
    }

    public Subject toModel(UpdateSubjectResource resource) {
        return mapper.map(resource, Subject.class);
    }
}

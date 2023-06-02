package com.totemcrew.teachers.mapping;

import com.totemcrew.shared.mapping.EnhancedModelMapper;
import com.totemcrew.teachers.domain.model.entity.Teacher;
import com.totemcrew.teachers.resource.CreateTeacherResource;
import com.totemcrew.teachers.resource.TeacherResource;
import com.totemcrew.teachers.resource.UpdateTeacherResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class TeacherMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public TeacherResource toResource(Teacher model) {
        return mapper.map(model, TeacherResource.class);
    }

    public Page<TeacherResource> modelListToPage(List<Teacher> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, TeacherResource.class), pageable, modelList.size());
    }

    public List<TeacherResource> modelListToResource(List<Teacher> modelList){
        return mapper.mapList(modelList, TeacherResource.class);
    }

    public Teacher toModel(CreateTeacherResource resource) {
        return mapper.map(resource, Teacher.class);
    }

    public Teacher toModel(UpdateTeacherResource resource) {
        return mapper.map(resource, Teacher.class);
    }
}

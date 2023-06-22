package com.totemcrew.students.mapping;

import com.totemcrew.shared.mapping.EnhancedModelMapper;
import com.totemcrew.students.domain.model.entity.Student;
import com.totemcrew.students.resource.CreateStudentResource;
import com.totemcrew.students.resource.StudentResource;
import com.totemcrew.students.resource.UpdateStudentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class StudentMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public StudentResource toResource(Student model) {
        return mapper.map(model, StudentResource.class);
    }

    public Page<StudentResource> modelListToPage(List<Student> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, StudentResource.class), pageable, modelList.size());
    }

    public List<StudentResource> modelListToResource(List<Student> modelList) {
        return mapper.mapList(modelList, StudentResource.class);
    }

    public Student toModel(CreateStudentResource resource) {
        return mapper.map(resource, Student.class);
    }

    public Student toModel(UpdateStudentResource resource) {
        return mapper.map(resource, Student.class);
    }
}

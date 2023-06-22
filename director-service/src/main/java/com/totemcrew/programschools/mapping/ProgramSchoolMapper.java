package com.totemcrew.programschools.mapping;

import com.totemcrew.programschools.domain.model.entity.ProgramSchool;
import com.totemcrew.programschools.resource.CreateProgramSchoolResource;
import com.totemcrew.programschools.resource.ProgramSchoolResource;
import com.totemcrew.programschools.resource.UpdateProgramSchoolResource;
import com.totemcrew.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class ProgramSchoolMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public ProgramSchoolResource toResource(ProgramSchool model) { return mapper.map(model, ProgramSchoolResource.class); }

    public Page<ProgramSchoolResource> modelListToPage(List<ProgramSchool> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, ProgramSchoolResource.class), pageable, modelList.size());
    }

    public List<ProgramSchoolResource> modelListToResource(List<ProgramSchool> modelList){return mapper.mapList(modelList, ProgramSchoolResource.class); }

    public ProgramSchool toModel(CreateProgramSchoolResource resource) {
        return mapper.map(resource, ProgramSchool.class);
    }

    public ProgramSchool toModel(UpdateProgramSchoolResource resource) {
        return mapper.map(resource, ProgramSchool.class);
    }
}

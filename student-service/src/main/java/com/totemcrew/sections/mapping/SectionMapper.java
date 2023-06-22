package com.totemcrew.sections.mapping;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import com.totemcrew.sections.domain.model.entity.Section;
import com.totemcrew.sections.resource.CreateSectionResource;
import com.totemcrew.sections.resource.SectionResource;
import com.totemcrew.sections.resource.UpdateSectionResource;
import com.totemcrew.shared.mapping.EnhancedModelMapper;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class SectionMapper implements Serializable {
    
    @Autowired
    EnhancedModelMapper mapper;

    public SectionResource toResource(Section model) {
        return mapper.map(model, SectionResource.class);
    }

    public Page<SectionResource> modelListToPage(List<Section> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, SectionResource.class), pageable, modelList.size());
    }

    public List<SectionResource> modelListToResource(List<Section> modelList) {
        return mapper.mapList(modelList, SectionResource.class);
    }

    public Section toModel(CreateSectionResource resource) {
        return mapper.map(resource, Section.class);
    }

    public Section toModel(UpdateSectionResource resource) {
        return mapper.map(resource, Section.class);
    }
}

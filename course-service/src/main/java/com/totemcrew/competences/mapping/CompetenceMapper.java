package com.totemcrew.competences.mapping;

import com.totemcrew.competences.domain.model.entity.Competence;
import com.totemcrew.competences.resources.CompetenceResource;
import com.totemcrew.competences.resources.CreateCompetenceResource;
import com.totemcrew.competences.resources.UpdateCompetenceResource;
import com.totemcrew.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class CompetenceMapper implements Serializable {
    @Autowired
    EnhancedModelMapper mapper;

    public CompetenceResource toResource(Competence model) {
        return mapper.map(model, CompetenceResource.class);
    }

    public Page<CompetenceResource> modelListToPage(List<Competence> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, CompetenceResource.class), pageable, modelList.size());
    }

    public List<CompetenceResource> modelListToResource(List<Competence> modelList) {
        return mapper.mapList(modelList, CompetenceResource.class);
    }

    public Competence toModel(CreateCompetenceResource resource) {
        return mapper.map(resource, Competence.class);
    }

    public Competence toModel(UpdateCompetenceResource resource) {
        return mapper.map(resource, Competence.class);
    }
}

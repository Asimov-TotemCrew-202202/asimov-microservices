package com.totemcrew.directors.mapping;

import com.totemcrew.directors.domain.model.entity.Principal;
import com.totemcrew.directors.resource.CreatePrincipalResource;
import com.totemcrew.directors.resource.PrincipalResource;
import com.totemcrew.directors.resource.UpdatePrincipalResource;
import com.totemcrew.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class PrincipalMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public PrincipalResource toResource(Principal model) {
        return mapper.map(model, PrincipalResource.class);
    }

    public Page<PrincipalResource> modelListToPage(List<Principal> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, PrincipalResource.class), pageable, modelList.size());
    }

    public List<PrincipalResource> modelListToResource(List<Principal> modelList){
        return mapper.mapList(modelList, PrincipalResource.class);
    }

    public Principal toModel(CreatePrincipalResource resource) {
        return mapper.map(resource, Principal.class);
    }

    public Principal toModel(UpdatePrincipalResource resource) {
        return mapper.map(resource, Principal.class);
    }
}

package com.totemcrew.statements.mapping;

import com.totemcrew.shared.mapping.EnhancedModelMapper;
import com.totemcrew.statements.domain.model.entity.Statement;
import com.totemcrew.statements.resource.CreateStatementResource;
import com.totemcrew.statements.resource.StatementResource;
import com.totemcrew.statements.resource.UpdateStatementResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class StatementMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public StatementResource toResource(Statement model) { return mapper.map(model, StatementResource.class); }

    public Page<StatementResource> modelListToPage(List<Statement> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, StatementResource.class), pageable, modelList.size());
    }

    public List<StatementResource> modelListToResource(List<Statement> modelList){return mapper.mapList(modelList, StatementResource.class); }

    public Statement toModel(CreateStatementResource resource) {
        return mapper.map(resource, Statement.class);
    }

    public Statement toModel(UpdateStatementResource resource) {
        return mapper.map(resource, Statement.class);
    }

}

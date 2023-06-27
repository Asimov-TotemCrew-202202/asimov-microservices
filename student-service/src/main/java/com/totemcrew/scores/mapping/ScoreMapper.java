package com.totemcrew.scores.mapping;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.totemcrew.scores.domain.model.Score;
import com.totemcrew.scores.resource.CreateScoreResource;
import com.totemcrew.scores.resource.ScoreResource;
import com.totemcrew.shared.mapping.EnhancedModelMapper;

public class ScoreMapper implements Serializable {
    
    @Autowired
    EnhancedModelMapper mapper;

    public ScoreResource toResource(Score model) {
        return mapper.map(model, ScoreResource.class);
    }

    public List<ScoreResource> modelListToResource(List<Score> modelList) {
        return mapper.mapList(modelList, ScoreResource.class);
    }

    public Score toModel(CreateScoreResource resource) {
        return mapper.map(resource, Score.class);
    }

}

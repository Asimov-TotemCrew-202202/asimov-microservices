package com.totemcrew.topics.mapping;

import com.totemcrew.topics.domain.model.entity.Topic;
import com.totemcrew.topics.resource.CreateTopicResource;
import com.totemcrew.topics.resource.TopicResource;
import com.totemcrew.topics.resource.UpdateTopicResource;
import com.totemcrew.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;
import java.util.List;

public class TopicMapper implements Serializable {

    @Autowired
    EnhancedModelMapper mapper;

    public TopicResource toResource(Topic model) {
        return mapper.map(model, TopicResource.class);
    }

    public Page<TopicResource> modelListToPage(List<Topic> modelList, Pageable pageable) {
        return new PageImpl<>(mapper.mapList(modelList, TopicResource.class), pageable, modelList.size());
    }

    public List<TopicResource> modelListToResource(List<Topic> modelList) {
        return mapper.mapList(modelList, TopicResource.class);
    }

    public Topic toModel(CreateTopicResource resource) {
        return mapper.map(resource, Topic.class);
    }

    public Topic toModel(UpdateTopicResource resource) {
        return mapper.map(resource, Topic.class);
    }
}

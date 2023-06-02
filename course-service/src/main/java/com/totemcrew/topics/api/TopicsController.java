package com.totemcrew.topics.api;

import com.totemcrew.topics.domain.service.TopicService;
import com.totemcrew.topics.mapping.TopicMapper;
import com.totemcrew.topics.resource.CreateTopicResource;
import com.totemcrew.topics.resource.TopicResource;
import com.totemcrew.topics.resource.UpdateTopicResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class TopicsController {

    private final TopicService topicService;

    private final TopicMapper mapper;

    public TopicsController(TopicService itemService, TopicMapper mapper) {
        this.topicService = itemService;
        this.mapper = mapper;
    }

    @GetMapping("courses/{courseId}/topics")
    public List<TopicResource> getAllByCourseID(@PathVariable Long courseId) {
        return mapper.modelListToResource(topicService.getAllByCourseId(courseId));
    }

    @PostMapping("courses/{courseId}/topics")
    public TopicResource createTopic(@RequestBody CreateTopicResource request, @PathVariable Long courseId) {
        return mapper.toResource(topicService.create(mapper.toModel(request),courseId));
    }


    @PutMapping("topics/{topicId}")
    public TopicResource updateTopic(@PathVariable Long topicId, @RequestBody UpdateTopicResource request) {
        return mapper.toResource(topicService.update(topicId, mapper.toModel(request)));
    }

    @DeleteMapping("topics/{topicId}")
    public ResponseEntity<?> deleteTopic(@PathVariable Long topicId) {
        return topicService.delete(topicId);
    }
}

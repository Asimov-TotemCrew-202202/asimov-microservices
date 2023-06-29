package com.totemcrew.exams.resource;

import com.totemcrew.topics.resource.TopicResource;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExamTopicResource {
    private Long id;
    private TopicResource topic;
}

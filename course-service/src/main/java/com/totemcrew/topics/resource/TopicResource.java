package com.totemcrew.topics.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TopicResource {

    private Long id;

    private String title;

    private String description;

    private String file;

    private int courseId;
}

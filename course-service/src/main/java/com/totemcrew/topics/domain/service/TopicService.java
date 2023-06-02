package com.totemcrew.topics.domain.service;

import com.totemcrew.topics.domain.model.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TopicService {
    List<Topic> getAllByCourseId(Long courseId);
    Page<Topic> getAllByCourseId(Long courseId, Pageable pageable);
    Topic getById(Long itemId);
    Topic create(Topic item, Long courseId);
    Topic update(Long itemId, Topic request);
    ResponseEntity<?> delete(Long itemId);
}

package com.totemcrew.topics.service;

import com.totemcrew.courses.domain.persistence.CourseRepository;
import com.totemcrew.topics.domain.model.entity.Topic;
import com.totemcrew.topics.domain.persistence.TopicRepository;
import com.totemcrew.topics.domain.service.TopicService;
import com.totemcrew.shared.exception.ResourceNotFoundException;
import com.totemcrew.shared.exception.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private CourseRepository courseRepository;

    private static final String ENTITY = "Item";
    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private Validator validator;


    @Override
    public List<Topic> getAllByCourseId(Long courseId) {
        var existingCourse =  courseRepository.findById(courseId);
        if(existingCourse.isEmpty())
            throw new ResourceNotFoundException("Course",courseId);
        return topicRepository.findByCourseId(courseId);
    }

    @Override
    public Page<Topic> getAllByCourseId(Long courseId, Pageable pageable) {
        return topicRepository.findByCourseId(courseId,pageable);
    }

    @Override
    public Topic getById(Long itemId) {
        return topicRepository.findById(itemId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, itemId));
    }

    @Override
    public Topic create(Topic item, Long courseId) {
        Set<ConstraintViolation<Topic>> violations = validator.validate(item);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return courseRepository.findById(courseId).map(course -> {
            item.setStatus(false);
            item.setCourse(course);
            return topicRepository.save(item);
        }).orElseThrow(()->new ResourceNotFoundException("Course ",courseId));
    }

    @Override
    public Topic completeTopic(Long topicId) {
       var topic = topicRepository.findById(topicId);
       if (topic.isEmpty())
           throw new ResourceValidationException("topic not exist");

       topic.get().setStatus(true);
       return topicRepository.save(topic.get());
    }

    @Override
    public Topic update(Long itemId, Topic request) {
        Set<ConstraintViolation<Topic>> violations = validator.validate(request);
        if (!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return topicRepository.findById(itemId).map(item ->
                topicRepository.save(item
                                .withTitle(request.getTitle())
                                .withFile(request.getFile()))
                        .withDescription(request.getDescription())
                        .withStatus(request.getStatus())
                ).orElseThrow(() -> new ResourceNotFoundException(ENTITY, itemId));
    }


    @Override
    public ResponseEntity<?> delete(Long itemId) {
        return topicRepository.findById(itemId).map(item -> {
            topicRepository.delete(item);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, itemId));
    }
}

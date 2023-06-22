package com.totemcrew.topics.domain.persistence;

import com.totemcrew.topics.domain.model.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    List<Topic> findByCourseId(long courseId);
    Page<Topic> findByCourseId(long courseId, Pageable pageable);
}

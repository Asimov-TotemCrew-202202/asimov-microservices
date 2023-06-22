package com.totemcrew.exams.domain.persistence;

import com.totemcrew.exams.domain.model.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {
    Exam findByTopicId(long courseId);
}

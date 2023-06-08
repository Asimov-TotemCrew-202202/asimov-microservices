package com.totemcrew.exams.domain.persistence;

import com.totemcrew.exams.domain.model.entity.ExamDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExamDetailRepository extends JpaRepository<ExamDetail, Long> {

    List<ExamDetail> findByExamId(Long examId);
}

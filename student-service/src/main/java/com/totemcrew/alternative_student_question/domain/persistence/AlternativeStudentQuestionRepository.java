package com.totemcrew.alternative_student_question.domain.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.totemcrew.alternative_student_question.domain.model.entity.AlternativeStudentQuestion;

@Repository
public interface AlternativeStudentQuestionRepository extends JpaRepository<AlternativeStudentQuestion, Long> {
    AlternativeStudentQuestion findByStudentIdAndExamDetailId(Long studentId, Long examDetailId);
}

package com.totemcrew.teachers.domain.persistence;

import com.totemcrew.teachers.domain.model.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    Teacher findByUserId(Long userId);
    List<Teacher> findByPrincipalId(Long principalId);

}

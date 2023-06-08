package com.totemcrew.students.domain.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.totemcrew.students.domain.model.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByUserId(Long userId);
    List<Student> findBySectionId(Long sectionId);
}

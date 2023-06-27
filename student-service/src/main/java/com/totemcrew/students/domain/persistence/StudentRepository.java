package com.totemcrew.students.domain.persistence;

import com.totemcrew.students.domain.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findBySectionId(Long sectionId);
    Student findByUserId(Long userId);
}

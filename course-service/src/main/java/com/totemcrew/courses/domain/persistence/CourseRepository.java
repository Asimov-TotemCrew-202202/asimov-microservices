package com.totemcrew.courses.domain.persistence;

import com.totemcrew.competences.domain.model.entity.Competence;
import com.totemcrew.courses.domain.model.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    @Query("select d from Course d where d.name = ?1")
    Course findByName(String name);

    @Query(value="SELECT c FROM Competence c JOIN c.courses t WHERE t.id = :courseId")
    List<Competence> getAllCompetences_Course(@Param("courseId")Long courseId);
}

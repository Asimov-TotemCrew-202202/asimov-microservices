package com.totemcrew.subjects.domain.persistence;

import com.totemcrew.subjects.domain.model.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findByPrincipalId(Long principalId);

}

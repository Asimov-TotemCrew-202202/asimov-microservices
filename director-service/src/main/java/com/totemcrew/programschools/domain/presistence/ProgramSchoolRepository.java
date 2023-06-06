package com.totemcrew.programschools.domain.presistence;

import com.totemcrew.programschools.domain.model.entity.ProgramSchool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgramSchoolRepository extends JpaRepository<ProgramSchool, Long> {
    List<ProgramSchool> findByPrincipalId(Long principalId);
    Page<ProgramSchool> findByPrincipalId(Long principalId, Pageable pageable);
}

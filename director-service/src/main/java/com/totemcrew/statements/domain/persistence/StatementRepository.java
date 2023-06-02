package com.totemcrew.statements.domain.persistence;

import com.totemcrew.statements.domain.model.entity.Statement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementRepository extends JpaRepository<Statement, Long> {

    List<Statement> findByPrincipalId(Long directorId);
    Page<Statement> findByPrincipalId(Long directorId, Pageable pageable);
}

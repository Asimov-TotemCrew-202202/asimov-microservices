package com.totemcrew.statements.domain.service;

import com.totemcrew.statements.domain.model.entity.Statement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StatementService {
    List<Statement> getAllByPrincipalId(Long directorId);
    Page<Statement> getAllByPrincipalId(Long directorId, Pageable pageable);
    Statement create(Long directorId, Statement request);
    Statement update(Long statementId, Statement request);
    ResponseEntity<?> delete(Long statementId);
}

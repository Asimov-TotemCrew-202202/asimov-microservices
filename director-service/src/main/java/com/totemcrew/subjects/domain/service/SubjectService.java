package com.totemcrew.subjects.domain.service;

import com.totemcrew.subjects.domain.model.entity.Subject;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


import java.util.List;
public interface SubjectService {

    List<Subject> getAll();
    Page<Subject> getAll(Pageable pageable);

    List<Subject> getAllByPrincipalId(Long principalId);

    Subject getById(Long SubjectId);
    Subject create(Long principalId, Subject Subject);
    Subject update(Long SubjectId, Subject Subject);

    ResponseEntity<?> delete(Long SubjectId);

}

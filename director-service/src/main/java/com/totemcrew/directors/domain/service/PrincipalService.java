package com.totemcrew.directors.domain.service;

import com.totemcrew.directors.domain.model.entity.Principal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PrincipalService {
    List<Principal> getAll();
    Page<Principal> getAll(Pageable pageable);
    Principal getById(Long directorId);
    Principal getByUserId(Long userId);
    Principal create(Principal director);
    Principal update(Long directorId, Principal director);
    ResponseEntity<?> delete(Long directorId);
}

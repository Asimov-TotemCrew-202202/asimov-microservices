package com.totemcrew.programschools.domain.service;

import com.totemcrew.programschools.domain.model.entity.ProgramSchool;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProgramSchoolService {

    List<ProgramSchool> getAllByPrincipalId(Long principalId);
    Page<ProgramSchool> getAllByPrincipalId(Long principalId, Pageable pageable);
    ProgramSchool create(Long principalId, ProgramSchool request);
    ProgramSchool update(Long programSchoolId, ProgramSchool request);
    ResponseEntity<?> delete(Long programSchoolId);
}

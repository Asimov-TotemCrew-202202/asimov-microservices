package com.totemcrew.programschools.api;

import com.totemcrew.programschools.domain.service.ProgramSchoolService;
import com.totemcrew.programschools.mapping.ProgramSchoolMapper;
import com.totemcrew.programschools.resource.CreateProgramSchoolResource;
import com.totemcrew.programschools.resource.ProgramSchoolResource;
import com.totemcrew.programschools.resource.UpdateProgramSchoolResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class ProgramSchoolsController {

    private final ProgramSchoolService programSchoolService;
    private final ProgramSchoolMapper mapper;

    public ProgramSchoolsController(ProgramSchoolService programSchoolService, ProgramSchoolMapper mapper) {
        this.programSchoolService = programSchoolService;
        this.mapper = mapper;
    }

    @GetMapping("principals/{principalId}/programSchools")
    public List<ProgramSchoolResource> getAllProgramSchoolsByDirectorId(@PathVariable Long principalId) {
        return mapper.modelListToResource(programSchoolService.getAllByPrincipalId(principalId));
    }

    @PostMapping("principals/{principalId}/programSchools")
    public ProgramSchoolResource createProgramSchool(@PathVariable Long principalId, @RequestBody CreateProgramSchoolResource request) {
        return mapper.toResource(programSchoolService.create(principalId, mapper.toModel(request)));
    }

    @PutMapping("programSchools/{programSchoolId}")
    public ProgramSchoolResource updateProgramSchool(@PathVariable Long programSchoolId, @RequestBody UpdateProgramSchoolResource request) {
        return mapper.toResource(programSchoolService.update(programSchoolId, mapper.toModel(request)));
    }

    @DeleteMapping("programSchools/{programSchoolId}")
    public ResponseEntity<?> deleteProgramSchool(@PathVariable Long programSchoolId) {
        return programSchoolService.delete(programSchoolId);
    }
}

package com.totemcrew.directors.api;

import com.totemcrew.directors.resource.CreatePrincipalResource;
import com.totemcrew.directors.domain.service.PrincipalService;
import com.totemcrew.directors.mapping.PrincipalMapper;
import com.totemcrew.directors.resource.PrincipalResource;
import com.totemcrew.directors.resource.UpdatePrincipalResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/principals")
public class PrincipalsController {

    @Autowired
    private PrincipalService principalService;

    @Autowired
    private PrincipalMapper mapper;

    @GetMapping
    public List<PrincipalResource> getAllDirectors() { return mapper.modelListToResource(principalService.getAll()); }

    @GetMapping("{directorId}")
    public PrincipalResource getDirectorById(@PathVariable("directorId") Long directorId) {
        return mapper.toResource(principalService.getById(directorId));
    }

    @GetMapping("/getByUser/{userId}")
    public PrincipalResource getDirectorByUserId(@PathVariable("userId") Long userId) {
        return mapper.toResource(principalService.getByUserId(userId));
    }

    @PostMapping("")
    public PrincipalResource createPrincipal(@RequestBody CreatePrincipalResource request) {
        return mapper.toResource(principalService.create(mapper.toModel(request)));
    }

    @PutMapping("{directorId}")
    public PrincipalResource updatePrincipal(@PathVariable Long directorId, @RequestBody UpdatePrincipalResource request) {
        return mapper.toResource(principalService.update(directorId, mapper.toModel(request)));
    }

    @DeleteMapping("{directorId}")
    public ResponseEntity<?> deletePrincipal(@PathVariable Long directorId) {
        return principalService.delete(directorId);
    }

}

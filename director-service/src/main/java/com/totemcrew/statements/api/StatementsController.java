package com.totemcrew.statements.api;

import com.totemcrew.statements.domain.service.StatementService;
import com.totemcrew.statements.mapping.StatementMapper;
import com.totemcrew.statements.resource.CreateStatementResource;
import com.totemcrew.statements.resource.StatementResource;
import com.totemcrew.statements.resource.UpdateStatementResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class StatementsController {

    private final StatementService statementService;

    private final StatementMapper mapper;

    public StatementsController(StatementService statementService, StatementMapper mapper) {
        this.statementService = statementService;
        this.mapper = mapper;
    }

    @GetMapping("principals/{principalId}/statements")
    public List<StatementResource> getAllAnnouncementsByDirectorId(@PathVariable Long principalId) {
        return mapper.modelListToResource(statementService.getAllByPrincipalId(principalId));
    }

    @PostMapping("principals/{principalId}/statements")
    public StatementResource createStatement(@PathVariable Long principalId, @RequestBody CreateStatementResource request) {
        return mapper.toResource(statementService.create(principalId, mapper.toModel(request)));
    }

    @PutMapping("statements/{statementId}")
    public StatementResource updateAnnouncement(@PathVariable Long statementId, @RequestBody UpdateStatementResource request) {
        return mapper.toResource(statementService.update(statementId, mapper.toModel(request)));
    }

    @DeleteMapping("statements/{statementId}")
    public ResponseEntity<?> deleteAnnouncement(@PathVariable Long statementId) {
        return statementService.delete(statementId);
    }
}

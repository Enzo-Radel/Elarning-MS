 package com.elearning.progresso.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.progresso.entity.Inscricao;
import com.elearning.progresso.service.CommandService;
import com.elearning.progresso.service.QueryService;

@RestController
@RequestMapping("/api/inscricoes")
public class InscricaoController {
    private final CommandService commandService;
    private final QueryService queryService;

    public InscricaoController(CommandService commandService, QueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    public ResponseEntity<Inscricao> create(@RequestBody Inscricao body) {
        Inscricao saved = commandService.createInscricao(body.getFuncionarioId(), body.getCursoId());
        return ResponseEntity.created(URI.create("/api/inscricoes/" + saved.getId())).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Inscricao> get(@PathVariable Long id) {
        Inscricao i = queryService.getInscricao(id);
        if (i == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(i);
    }
}

package com.elearning.progresso.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.progresso.entity.ProgressoModulo;
import com.elearning.progresso.service.CommandService;
import com.elearning.progresso.service.QueryService;

@RestController
@RequestMapping("/api/progresso")
public class ProgressoController {
    private final CommandService commandService;
    private final QueryService queryService;

    public ProgressoController(CommandService commandService, QueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping("/inscricao/{inscricaoId}/modulos/{moduloId}/concluir")
    public ResponseEntity<ProgressoModulo> concluirModulo(@PathVariable Long inscricaoId, @PathVariable String moduloId, @RequestParam(defaultValue = "300") long tempo) {
        ProgressoModulo p = commandService.completeModulo(inscricaoId, moduloId, tempo);
        return ResponseEntity.ok(p);
    }
}

package br.com.gestao_residuos_automacao.controller;

import br.com.gestao_residuos_automacao.model.AgendamentoColeta;
import br.com.gestao_residuos_automacao.service.AgendamentoColetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import br.com.gestao_residuos_automacao.exception.RotaNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/agendamento-de-coleta")
public class AgendamentoColetaController {

    private final AgendamentoColetaService agendamentoColetaService;

    @Autowired
    public AgendamentoColetaController(AgendamentoColetaService agendamentoColetaService) {
        this.agendamentoColetaService = agendamentoColetaService;
    }

    // Endpoint para recuperar todos os agendamentos de coleta
    @Operation(summary = "Recuperar todos os agendamentos de coleta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the agendamentos",
                content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = AgendamentoColeta.class)) })
    })
    @GetMapping
    public ResponseEntity<List<AgendamentoColeta>> getAllAgendamentos() {
        List<AgendamentoColeta> agendamentos = agendamentoColetaService.getAllAgendamentos();
        return new ResponseEntity<>(agendamentos, HttpStatus.OK);
    }

    // Endpoint para recuperar um agendamento de coleta por ID
    @Operation(summary = "Recuperar um agendamento de coleta por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the agendamento",
                content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = AgendamentoColeta.class)) }),
        @ApiResponse(responseCode = "404", description = "Agendamento not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoColeta> getAgendamentoById(@PathVariable Long id) {
        Optional<AgendamentoColeta> agendamento = agendamentoColetaService.getAgendamentoById(id);
        return agendamento.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Endpoint para agendar uma nova coleta
    @Operation(summary = "Agendar uma nova coleta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Agendamento created",
                content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = AgendamentoColeta.class)) })
    })
    @PostMapping
    public ResponseEntity<?> agendarColeta(@Valid @RequestBody AgendamentoColeta agendamentoColeta, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            for (FieldError error : result.getFieldErrors()) {
                errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body(errors.toString());
        }

        try {
            AgendamentoColeta novoAgendamento = agendamentoColetaService.salvarAgendamento(agendamentoColeta);
            return new ResponseEntity<>(novoAgendamento, HttpStatus.CREATED);

        } catch (RotaNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Endpoint para atualizar um agendamento de coleta existente
    @Operation(summary = "Atualizar um agendamento de coleta existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Agendamento updated",
                content = { @Content(mediaType = "application/json",
                schema = @Schema(implementation = AgendamentoColeta.class)) }),
        @ApiResponse(responseCode = "404", description = "Agendamento not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarAgendamento(
            @PathVariable Long id, @Valid @RequestBody AgendamentoColeta novoAgendamentoColeta, BindingResult result) {
        if (result.hasErrors()) {
            StringBuilder errors = new StringBuilder();
            for (FieldError error : result.getFieldErrors()) {
                errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ");
            }
            return ResponseEntity.badRequest().body(errors.toString());
        }

        try {

            AgendamentoColeta agendamentoAtualizado = agendamentoColetaService.atualizarAgendamento(id, novoAgendamentoColeta);
            if (agendamentoAtualizado != null) {
                return new ResponseEntity<>(agendamentoAtualizado, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (RotaNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    // Endpoint para cancelar um agendamento de coleta
    @Operation(summary = "Cancelar um agendamento de coleta")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Agendamento canceled", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarAgendamento(@PathVariable Long id) {
        agendamentoColetaService.cancelarAgendamento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

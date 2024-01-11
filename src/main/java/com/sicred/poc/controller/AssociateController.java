package com.sicred.poc.controller;

import com.sicred.poc.external.dto.AgendaDTO;
import com.sicred.poc.external.dto.AssociateDTO;
import com.sicred.poc.external.dto.AssociateSaveDTO;
import com.sicred.poc.service.IAssociateService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/associate")
@AllArgsConstructor
@CrossOrigin("*")
public class AssociateController {

    private IAssociateService service;

    @Operation(summary = "Listagem de Assoaciados",
            description = "Listagem de registros",
            tags = {"Listagem de registros"})
    @ApiResponse(responseCode = "200", description = "Listagem de registros")
    @ApiResponses(value = {
            @ApiResponse(content = {
                    @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = AssociateDTO.class))
                    )})})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/all")
    public ResponseEntity<List<AssociateDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Pesquisa por nome de Assoaciado",
            description = "Pesquisa por nome",
            tags = {"Pesquisa por nome"})
    @ApiResponse(responseCode = "200", description = "Pesquisa por nome")
    @ApiResponses(value = {
            @ApiResponse(content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AssociateDTO.class))
            })})
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{name}")
    public ResponseEntity<AssociateDTO> findByName(@PathParam("name") String name) {
        return ResponseEntity.ok(service.findByName(name));
    }

    @Operation(summary = "Inclusão de Assoaciado",
            description = "Criação de Assoaciado",
            tags = {"Criação de registro"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o Assoaciado se já houver o " +
                    "nome informado cadastrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgendaDTO.class))),
            @ApiResponse(responseCode = "201", description = "Retorna o Assoaciado Salvo",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AssociateDTO.class)))
    })
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/save")
    public ResponseEntity<AssociateDTO> save(@RequestBody AssociateSaveDTO associateSaveDTO) {
        return service.save(associateSaveDTO);
    }

    @Operation(summary = "Atualizão de Assoaciado",
            description = "Atualizão de Assoaciado",
            tags = {"Atualização"})
    @ApiResponse(responseCode = "200", description = "Atualizão de Assoaciado será efetivada somente se " +
            "ID informado for válido")
    @ApiResponses(value = {
            @ApiResponse(content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AgendaDTO.class))
            })})
    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/update")
    public ResponseEntity<AssociateDTO> update(@RequestBody AssociateDTO associateDTO) {
        return ResponseEntity.ok(service.update(associateDTO));
    }

    @Operation(summary = "Deleção de Assoaciado pelo Id",
            description = "Deleção de Assoaciado pelo Id",
            tags = {"Delete"})
    @ApiResponse(responseCode = "200", description = "Deleção de Assoaciado pelo Id")
    @ApiResponses(value = {
            @ApiResponse(content = {
                    @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Object.class))
            })})
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE, path = "/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}

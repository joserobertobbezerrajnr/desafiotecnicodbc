package com.sicred.poc.service.impl;

import com.sicred.poc.exception.PocAssembleiaException;
import com.sicred.poc.exception.PocSicredErrors;
import com.sicred.poc.external.dto.AgendaDTO;
import com.sicred.poc.mapper.AgendaMapper;
import com.sicred.poc.model.AgendaEntity;
import com.sicred.poc.repository.AgendaRepository;
import com.sicred.poc.template.AgendaTemplate;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyList;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AgendaServiceTest {

    @InjectMocks
    private AgendaService service;
    @Mock
    private AgendaRepository repository;
    @Mock
    private AgendaMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should Pass When Agendas Is Valid")
    void testShouldPassWhenAgendasIsValid() {
        AgendaDTO agendaDTO = AgendaTemplate.validAgendaDTO();
        AgendaEntity agendaEntity = mapper.to(agendaDTO);
        when(repository.findAll()).thenReturn(Arrays.asList(agendaEntity));
        when(mapper.from(anyList())).thenReturn(Arrays.asList(agendaDTO));
        List<AgendaDTO> agendas = service.getAll();
        assertTrue(agendas.size() > 0, "Assertion fail, list is empty");
    }

    @Test
    @DisplayName("Should Pass When GetAll Throw New AGENDA_NOT_FOUND")
    @SneakyThrows
    void testShouldPassWhenGetAllThrowAgendaNotFound() {
        when(repository.findAll()).thenReturn(Collections.EMPTY_LIST);
        PocAssembleiaException error = assertThrows(PocAssembleiaException.class, () ->
                service.getAll(), "Assertion fail, Exception not throws");
        assertEquals(PocSicredErrors.AGENDA_NOT_FOUND.getMessage(), error.getMessage(),
                "Assertion fail, message error invalid");
    }

    @Test
    @DisplayName("Should Pass When Throw AGENDA_NOT_FOUND")
    @SneakyThrows
    void testShouldPassWhenFindByTitleThrowAgendaNotFound() {
        when(repository.findByTitle(anyString())).thenReturn(Optional.empty());
        PocAssembleiaException error = assertThrows(PocAssembleiaException.class, () ->
                service.findByTitle("teste"), "Assertion fail, Exception not throws");
        assertEquals(PocSicredErrors.AGENDA_NOT_FOUND.getMessage(), error.getMessage(),
                "Assertion fail, message error invalid");
    }

    @Test
    @DisplayName("Should Pass When FindByTitle AgendaDTO Is Valid")
    void testShouldPassWhenFindByTitleAgendaDTOIsValid() {
        AgendaEntity agendaEntity = AgendaTemplate.validAgendaEntity();
        when(repository.findByTitle(anyString())).thenReturn(Optional.of(agendaEntity));
        when(mapper.from(any(AgendaEntity.class))).thenReturn(AgendaTemplate.validAgendaDTO());
        AgendaDTO agendaDTO = service.findByTitle("teste");
        assertEquals(agendaEntity.getId(), agendaDTO.getId(),
                "Assertion fail, value invalid");
        assertEquals(agendaEntity.getTitle(), agendaDTO.getTitle(),
                "Assertion fail, value invalid");
        assertEquals(agendaEntity.getDescription(), agendaDTO.getDescription(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When Response Status CREATED")
    void testShouldPassWhenResponseStatusCREATED() {
        AgendaEntity agendaEntityMock = AgendaTemplate.validAgendaEntity();
        when(repository.findByTitle(anyString()))
                .thenReturn(Optional.empty());
        when(mapper.toSave(any())).thenReturn(agendaEntityMock);
        when(repository.save(any())).thenReturn(agendaEntityMock);
        when(mapper.from(any(AgendaEntity.class))).thenReturn(AgendaTemplate.validAgendaDTO());

        ResponseEntity<AgendaDTO> response = service.save(AgendaTemplate.validAgendaSaveDTO());
        assertEquals(HttpStatus.CREATED, response.getStatusCode(),
                "Assertion fail, status code invalid");
    }

    @Test
    @DisplayName("Should Pass When Response Status OK")
    void testShouldPassWhenResponseStatusOK() {
        AgendaEntity agendaEntityMock = AgendaTemplate.validAgendaEntity();
        when(repository.findByTitle(anyString()))
                .thenReturn(Optional.of(agendaEntityMock));

        ResponseEntity<AgendaDTO> response = service.save(AgendaTemplate.validAgendaSaveDTO());
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Assertion fail, status code invalid");
    }

    @Test
    @DisplayName("Should Pass When Update AgendaDTO Is Valid")
    void testShouldPassWhenUpdateAgendaDTOIsValid() {
        AgendaDTO agendaDTOMock = AgendaTemplate.validAgendaDTO();
        AgendaEntity agendaEntity = AgendaTemplate.validAgendaEntity();
        when(repository.findById(any())).thenReturn(Optional.of(agendaEntity));
        when(mapper.toUpdate(any(), any())).thenReturn(agendaEntity);
        when(repository.save(any())).thenReturn(agendaEntity);
        when(mapper.from(any(AgendaEntity.class))).thenReturn(agendaDTOMock);

        AgendaDTO agendaDTO = service.update(agendaDTOMock);
        assertEquals(agendaDTOMock.getId(), agendaDTO.getId(),
                "Assertion fail, value invalid");
        assertEquals(agendaDTOMock.getTitle(), agendaDTO.getTitle(),
                "Assertion fail, value invalid");
        assertEquals(agendaDTOMock.getDescription(), agendaDTO.getDescription(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When Throw AGENDA_NOT_UPDATED")
    @SneakyThrows
    void testShouldPassWhenThrowAgendaNotUpdated() {
        AgendaDTO agendaDTOMock = AgendaTemplate.validAgendaDTO();
        AgendaEntity agendaEntity = AgendaTemplate.validAgendaEntity();
        when(repository.findById(any())).thenReturn(Optional.of(agendaEntity));
        when(mapper.toUpdate(any(), any())).thenReturn(agendaEntity);
        when(repository.save(any()))
                .thenReturn(Optional.of(new PocAssembleiaException(PocSicredErrors.AGENDA_NOT_UPDATED)));

        ClassCastException error = assertThrows(ClassCastException.class, () ->
                service.update(agendaDTOMock), "Assertion fail, Exception not throws");
        assertFalse(error.getMessage().isEmpty(), "Assertion fail, not exits message error");
    }

    @Test
    @DisplayName("Should Pass When DeleteById Is Valid")
    void testShouldPassWhenDeleteByIdIsValid() {
        service.deleteById(1L);
        verify(repository, atMostOnce()).deleteById(1L);
    }

}

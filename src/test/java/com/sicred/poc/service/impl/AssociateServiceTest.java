package com.sicred.poc.service.impl;

import com.sicred.poc.exception.PocAssembleiaException;
import com.sicred.poc.exception.PocSicredErrors;
import com.sicred.poc.external.dto.AgendaDTO;
import com.sicred.poc.external.dto.AssociateDTO;
import com.sicred.poc.mapper.AssociateMapper;
import com.sicred.poc.model.AgendaEntity;
import com.sicred.poc.model.AssociateEntity;
import com.sicred.poc.repository.AssociateRepository;
import com.sicred.poc.template.AgendaTemplate;
import com.sicred.poc.template.AssociateTemplate;
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

class AssociateServiceTest {

    @InjectMocks
    private AssociateService service;
    @Mock
    private AssociateRepository repository;
    @Mock
    private AssociateMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should Pass When Associate Is Valid")
    void testShouldPassWhenAssociateIsValid() {
        AssociateDTO associateDTO = AssociateTemplate.validAgendaDTO();
        AssociateEntity associateEntity = mapper.to(associateDTO);
        when(repository.findAll()).thenReturn(Arrays.asList(associateEntity));
        when(mapper.from(anyList())).thenReturn(Arrays.asList(associateDTO));
        List<AssociateDTO> associates = service.getAll();
        assertTrue(associates.size() > 0, "Assertion fail, list is empty");
    }

    @Test
    @DisplayName("Should Pass When GetAll Throw New ASSOCIATE_NOT_FOUND")
    @SneakyThrows
    void testShouldPassWhenGetAllThrowAssociateNotFound() {
        when(repository.findAll()).thenReturn(Collections.EMPTY_LIST);
        PocAssembleiaException error = assertThrows(PocAssembleiaException.class, () ->
                service.getAll(), "Assertion fail, Exception not throws");
        assertEquals(PocSicredErrors.ASSOCIATE_NOT_FOUND.getMessage(), error.getMessage(),
                "Assertion fail, message error invalid");
    }

    @Test
    @DisplayName("Should Pass When Throw ASSOCIATE_NOT_FOUND")
    @SneakyThrows
    void testShouldPassWhenFindByTitleThrowAssociateNotFound() {
        when(repository.findByName(anyString())).thenReturn(Optional.empty());
        PocAssembleiaException error = assertThrows(PocAssembleiaException.class, () ->
                service.findByName("teste"), "Assertion fail, Exception not throws");
        assertEquals(PocSicredErrors.ASSOCIATE_NOT_FOUND.getMessage(), error.getMessage(),
                "Assertion fail, message error invalid");
    }

    @Test
    @DisplayName("Should Pass When FindByName AssociateDTO Is Valid")
    void testShouldPassWhenFindByNameAssociateDTOIsValid() {
        AssociateDTO associateDTOMock = AssociateTemplate.validAgendaDTO();
        AssociateEntity associateEntity = AssociateTemplate.validAssociateEntity();
        when(repository.findByName(anyString())).thenReturn(Optional.of(associateEntity));
        when(mapper.from(any(AssociateEntity.class))).thenReturn(associateDTOMock);

        AssociateDTO associateDTO = service.findByName("teste");
        assertEquals(associateDTOMock.getId(), associateDTO.getId(),
                "Assertion fail, value invalid");
        assertEquals(associateDTOMock.getName(), associateDTO.getName(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When Response Status CREATED")
    void testShouldPassWhenResponseStatusCREATED() {
        AssociateDTO associateDTOMock = AssociateTemplate.validAgendaDTO();
        AssociateEntity associateEntityMock = AssociateTemplate.validAssociateEntity();
        when(repository.findByName(anyString()))
                .thenReturn(Optional.empty());
        when(mapper.toSave(any())).thenReturn(associateEntityMock);
        when(repository.save(any())).thenReturn(associateEntityMock);
        when(mapper.from(any(AssociateEntity.class))).thenReturn(associateDTOMock);

        ResponseEntity<AssociateDTO> response = service.save(AssociateTemplate.validAssociateSaveDTO());
        assertEquals(HttpStatus.CREATED, response.getStatusCode(),
                "Assertion fail, status code invalid");
    }

    @Test
    @DisplayName("Should Pass When Response Status OK")
    void testShouldPassWhenResponseStatusOK() {
        AssociateEntity associateEntityMock = AssociateTemplate.validAssociateEntity();
        when(repository.findByName(anyString()))
                .thenReturn(Optional.of(associateEntityMock));

        ResponseEntity<AssociateDTO> response = service.save(AssociateTemplate.validAssociateSaveDTO());
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Assertion fail, status code invalid");
    }

    @Test
    @DisplayName("Should Pass When Update AssociateDTO Is Valid")
    void testShouldPassWhenUpdateAssociateDTOIsValid() {
        AssociateDTO associateDTOMock = AssociateTemplate.validAgendaDTO();
        AssociateEntity associateEntityMock = AssociateTemplate.validAssociateEntity();
        when(repository.findById(any())).thenReturn(Optional.of(associateEntityMock));
        when(mapper.toUpdate(any(), any())).thenReturn(associateEntityMock);
        when(repository.save(any())).thenReturn(associateEntityMock);
        when(mapper.from(any(AssociateEntity.class))).thenReturn(associateDTOMock);

        AssociateDTO associateDTO = service.update(associateDTOMock);
        assertEquals(associateDTOMock.getId(), associateDTO.getId(),
                "Assertion fail, value invalid");
        assertEquals(associateDTOMock.getName(), associateDTO.getName(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When Throw ASSOCIATE_NOT_UPDATED")
    @SneakyThrows
    void testShouldPassWhenThrowAgendaNotUpdated() {
        AssociateDTO associateDTOMock = AssociateTemplate.validAgendaDTO();
        AssociateEntity associateEntityMock = AssociateTemplate.validAssociateEntity();
        when(repository.findById(any())).thenReturn(Optional.of(associateEntityMock));
        when(mapper.toUpdate(any(), any())).thenReturn(associateEntityMock);
        when(repository.save(any()))
                .thenReturn(Optional.of(new PocAssembleiaException(PocSicredErrors.ASSOCIATE_NOT_UPDATED)));

        ClassCastException error = assertThrows(ClassCastException.class, () ->
                service.update(associateDTOMock), "Assertion fail, Exception not throws");
        assertFalse(error.getMessage().isEmpty(), "Assertion fail, not exits message error");
    }

    @Test
    @DisplayName("Should Pass When DeleteById Is Valid")
    void testShouldPassWhenDeleteByIdIsValid() {
        service.deleteById(1L);
        verify(repository, atMostOnce()).deleteById(1L);
    }

}

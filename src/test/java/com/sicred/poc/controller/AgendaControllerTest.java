package com.sicred.poc.controller;

import com.sicred.poc.external.dto.AgendaDTO;
import com.sicred.poc.external.dto.AgendaSaveDTO;
import com.sicred.poc.service.impl.AgendaService;
import com.sicred.poc.template.AgendaTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class AgendaControllerTest {

    @InjectMocks
    private AgendaController controller;
    @Mock
    private AgendaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should Pass When GetAll OK")
    void testShouldPassWhenGetAllOK() {
        ResponseEntity<List<AgendaDTO>> response = controller.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Assertion fail, response status invalid");
    }

    @Test
    @DisplayName("Should Pass When FindByTitle OK")
    void testShouldPassWhenFindByTitleOK() {
        ResponseEntity<AgendaDTO> response = controller.findByTitle("Titulo da pauta teste");
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Assertion fail, response status invalid");
    }

    @Test
    @DisplayName("Should Pass When Save CREATED")
    void testShouldPassWhenSaveCREATED() {
        AgendaSaveDTO agendaSaveDTO = AgendaTemplate.validAgendaSaveDTO();
        when(service.save(any())).thenReturn(ResponseEntity.created(URI.create("/agenda/save"))
                .body(AgendaTemplate.validAgendaDTO()));
        ResponseEntity<AgendaDTO> response = controller.save(agendaSaveDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode(),
                "Assertion fail, response status invalid");
    }

    @Test
    @DisplayName("Should Pass When Update OK")
    void testShouldPassWhenUpdateOK() {
        ResponseEntity<AgendaDTO> response = controller.update(AgendaTemplate.validAgendaDTO());
        when(service.update(any())).thenReturn(AgendaTemplate.validAgendaDTO());
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Assertion fail, response status invalid");
    }

    @Test
    @DisplayName("Should Pass When Delete OK")
    void testShouldPassWhenDeleteOK() {
        ResponseEntity<Object> response = controller.deleteById(1L);
        verify(service, atMostOnce()).deleteById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Assertion fail, response status invalid");
    }

}
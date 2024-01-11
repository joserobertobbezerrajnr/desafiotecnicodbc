package com.sicred.poc.controller;

import com.sicred.poc.external.dto.AssociateDTO;
import com.sicred.poc.external.dto.AssociateSaveDTO;
import com.sicred.poc.service.impl.AssociateService;
import com.sicred.poc.template.AssociateTemplate;
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
class AssociateControllerTest {

    @InjectMocks
    private AssociateController controller;
    @Mock
    private AssociateService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should Pass When GetAll OK")
    void testShouldPassWhenGetAllOK() {
        ResponseEntity<List<AssociateDTO>> response = controller.getAll();
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Assertion fail, response status invalid");
    }

    @Test
    @DisplayName("Should Pass When FindByTitle OK")
    void testShouldPassWhenFindByTitleOK() {
        ResponseEntity<AssociateDTO> response = controller.findByName("Name do associado teste");
        assertEquals(HttpStatus.OK, response.getStatusCode(),
                "Assertion fail, response status invalid");
    }

    @Test
    @DisplayName("Should Pass When Save CREATED")
    void testShouldPassWhenSaveCREATED() {
        AssociateSaveDTO associateSaveDTO = AssociateTemplate.validAssociateSaveDTO();
        when(service.save(any())).thenReturn(ResponseEntity.created(URI.create("/associate/save"))
                .body(AssociateTemplate.validAgendaDTO()));
        ResponseEntity<AssociateDTO> response = controller.save(associateSaveDTO);
        assertEquals(HttpStatus.CREATED, response.getStatusCode(),
                "Assertion fail, response status invalid");
    }

    @Test
    @DisplayName("Should Pass When Update OK")
    void testShouldPassWhenUpdateOK() {
        ResponseEntity<AssociateDTO> response = controller.update(AssociateTemplate.validAgendaDTO());
        when(service.update(any())).thenReturn(AssociateTemplate.validAgendaDTO());
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
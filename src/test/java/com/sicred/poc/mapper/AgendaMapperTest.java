package com.sicred.poc.mapper;

import com.sicred.poc.external.dto.AgendaDTO;
import com.sicred.poc.external.dto.AgendaSaveDTO;
import com.sicred.poc.model.AgendaEntity;
import com.sicred.poc.template.AgendaTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AgendaMapperTest {

    @InjectMocks
    private AgendaMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should Pass When AgendaEntity Is Valid")
    void testShouldPassWhenAgendaEntityIsValid() {
        AgendaDTO agendaDTOMock = AgendaTemplate.validAgendaDTO();
        AgendaEntity agendaEntity = mapper.to(agendaDTOMock);
        assertEquals(agendaDTOMock.getId(), agendaEntity.getId(),
                "Assertion fail, value invalid");
        assertEquals(agendaDTOMock.getTitle(), agendaEntity.getTitle(),
                "Assertion fail, value invalid");
        assertEquals(agendaDTOMock.getDescription(), agendaEntity.getDescription(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When List<AgendaDTO> Is Valid")
    void testShouldPassWhenAgendasIsValid() {
        AgendaEntity agendaEntityMock = AgendaTemplate.validAgendaEntity();
        List<AgendaDTO> agendas = mapper.from(List.of(agendaEntityMock));
        AgendaDTO agendaDTO = agendas.get(0);
        assertEquals(agendaEntityMock.getId(), agendaDTO.getId(),
                "Assertion fail, value invalid");
        assertEquals(agendaEntityMock.getTitle(), agendaDTO.getTitle(),
                "Assertion fail, value invalid");
        assertEquals(agendaEntityMock.getDescription(), agendaDTO.getDescription(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When AgendaDTO Is Valid")
    void testShouldPassWhenAgendaDTOIsValid() {
        AgendaEntity agendaEntityMock = AgendaTemplate.validAgendaEntity();
        AgendaDTO agendaDTO = mapper.from(agendaEntityMock);
        assertEquals(agendaEntityMock.getId(), agendaDTO.getId(),
                "Assertion fail, value invalid");
        assertEquals(agendaEntityMock.getTitle(), agendaDTO.getTitle(),
                "Assertion fail, value invalid");
        assertEquals(agendaEntityMock.getDescription(), agendaDTO.getDescription(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When ToSave AgendaEntity Is Valid")
    void testShouldPassWhenToSaveAgendaEntityIsValid() {
        AgendaSaveDTO agendaSaveDTOMock = AgendaTemplate.validAgendaSaveDTO();
        AgendaEntity agendaEntity = mapper.toSave(agendaSaveDTOMock);
        assertEquals(agendaSaveDTOMock.getTitle(), agendaEntity.getTitle(),
                "Assertion fail, value invalid");
        assertEquals(agendaSaveDTOMock.getDescription(), agendaEntity.getDescription(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When ToUpdate AgendaEntity Is Valid")
    void testShouldPassWhenToUpdateAgendaEntityIsValid() {
        AgendaEntity agendaOldMock = AgendaTemplate.validAgendaEntity();
        AgendaDTO agendaNewMock = AgendaTemplate.validAgendaDTO();
        agendaNewMock.setTitle("test update");
        agendaNewMock.setDescription("test update");
        AgendaEntity agendaEntity = mapper.toUpdate(agendaOldMock, agendaNewMock);
        assertEquals(agendaOldMock.getId(), agendaEntity.getId(),
                "Assertion fail, value invalid");
        assertNotEquals(agendaOldMock.getTitle(), agendaEntity.getTitle(),
                "Assertion fail, value invalid");
        assertNotEquals(agendaOldMock.getDescription(), agendaEntity.getDescription(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When ToUpdate Object Equals")
    void testShouldPassWhenToUpdateThrowIdIsNull() {
        AgendaEntity agendaOldMock = AgendaTemplate.validAgendaEntity();
        AgendaDTO agendaNewMock = AgendaTemplate.validAgendaDTO();
        AgendaEntity agendaEntity = mapper.toUpdate(agendaOldMock, agendaNewMock);
        assertEquals(agendaOldMock.getId(), agendaEntity.getId(),
                "Assertion fail, value invalid");
        assertEquals(agendaOldMock.getTitle(), agendaEntity.getTitle(),
                "Assertion fail, value invalid");
        assertEquals(agendaOldMock.getDescription(), agendaEntity.getDescription(),
                "Assertion fail, value invalid");
    }
}

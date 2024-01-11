package com.sicred.poc.mapper;

import com.sicred.poc.external.dto.AssociateDTO;
import com.sicred.poc.external.dto.AssociateSaveDTO;
import com.sicred.poc.model.AssociateEntity;
import com.sicred.poc.template.AssociateTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class AssociateMapperTest {

    @InjectMocks
    private AssociateMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should Pass When To AssociateEntity Is Valid")
    void testShouldPassWhenToAssociateEntityIsValid() {
        AssociateDTO associateDTOMock = AssociateTemplate.validAgendaDTO();
        AssociateEntity associateEntity = mapper.to(associateDTOMock);
        assertEquals(associateDTOMock.getId(), associateEntity.getId(),
                "Assertion fail, value invalid");
        assertEquals(associateDTOMock.getName(), associateEntity.getName(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When From Associates Is Valid")
    void testShouldPassWhenFromAssociatesIsValid() {
        AssociateEntity associateEntityMock = AssociateTemplate.validAssociateEntity();
        List<AssociateDTO> associates = mapper.from(List.of(associateEntityMock));
        AssociateDTO associateDTO = associates.get(0);
        assertEquals(associateEntityMock.getId(), associateDTO.getId(),
                "Assertion fail, value invalid");
        assertEquals(associateEntityMock.getName(), associateDTO.getName(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When From AssociateDTO Is Valid")
    void testShouldPassWhenFromAssociateDTOIsValid() {
        AssociateEntity associateEntityMock = AssociateTemplate.validAssociateEntity();
        AssociateDTO associateDTO = mapper.from(associateEntityMock);
        assertEquals(associateEntityMock.getId(), associateDTO.getId(),
                "Assertion fail, value invalid");
        assertEquals(associateEntityMock.getName(), associateDTO.getName(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When ToSave AssociateEntity Is Valid")
    void testShouldPassWhenToSaveAssociateEntityIsValid() {
        AssociateSaveDTO associateSaveDTOMock = AssociateTemplate.validAssociateSaveDTO();
        AssociateEntity associateEntity = mapper.toSave(associateSaveDTOMock);
        assertEquals(associateSaveDTOMock.getName(), associateEntity.getName(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When ToUpdate AssociateEntity Is Valid With Updated")
    void testShouldPassWhenToUpdateAssociateEntityIsValidWithUpdated() {
        AssociateEntity associateOldMock = AssociateTemplate.validAssociateEntity();
        AssociateDTO associateNewMock = AssociateTemplate.validAgendaDTO();
        associateNewMock.setName("test update");
        AssociateEntity associateEntity = mapper.toUpdate(associateOldMock, associateNewMock);
        assertEquals(associateOldMock.getId(), associateEntity.getId(),
                "Assertion fail, value invalid");
        assertNotEquals(associateOldMock.getName(), associateEntity.getName(),
                "Assertion fail, value invalid");
    }

}
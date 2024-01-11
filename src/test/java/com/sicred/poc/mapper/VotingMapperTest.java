package com.sicred.poc.mapper;

import com.sicred.poc.exception.PocAssembleiaException;
import com.sicred.poc.exception.PocSicredErrors;
import com.sicred.poc.external.dto.AgendaDTO;
import com.sicred.poc.external.dto.AssociateDTO;
import com.sicred.poc.external.dto.VotingDTO;
import com.sicred.poc.external.dto.VotingSavedDTO;
import com.sicred.poc.model.AgendaEntity;
import com.sicred.poc.model.AssociateEntity;
import com.sicred.poc.model.VotingEntity;
import com.sicred.poc.template.AgendaTemplate;
import com.sicred.poc.template.AssociateTemplate;
import com.sicred.poc.template.VotingTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

class VotingMapperTest {

    @InjectMocks
    private VotingMapper mapper;
    @Mock
    private AssociateMapper associateMapper;
    @Mock
    private AgendaMapper agendaMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should Pass When To VotingEntity Is Valid")
    void testShouldPassWhenToVotingEntityIsValid() {
        AssociateDTO associateDTOMock = AssociateTemplate.validAgendaDTO();
        AgendaDTO agendaDTOMock = AgendaTemplate.validAgendaDTO();
        VotingSavedDTO votingSavedDTOMock = VotingTemplate.validVotingSavedDTO();
        when(associateMapper.to(any())).thenReturn(AssociateTemplate.validAssociateEntity());
        when(agendaMapper.to(any())).thenReturn(AgendaTemplate.validAgendaEntity());

        VotingEntity votingEntity = mapper.to(associateDTOMock, agendaDTOMock, votingSavedDTOMock);
        AssociateEntity associateEntity = votingEntity.getAssociate();
        AgendaEntity agendaEntity = votingEntity.getAgenda();
        assertEquals(associateDTOMock.getId(), associateEntity.getId(),
                "Assertion fail, value invalid");
        assertEquals(associateDTOMock.getName(), associateEntity.getName(),
                "Assertion fail, value invalid");
        assertEquals(agendaDTOMock.getId(), agendaEntity.getId(),
                "Assertion fail, value invalid");
        assertEquals(agendaDTOMock.getTitle(), agendaEntity.getTitle(),
                "Assertion fail, value invalid");
        assertEquals(agendaDTOMock.getDescription(), agendaEntity.getDescription(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When To VotingEntity Is Valid No")
    void testShouldPassWhenToVotingEntityIsValidNo() {
        AssociateDTO associateDTOMock = AssociateTemplate.validAgendaDTO();
        AgendaDTO agendaDTOMock = AgendaTemplate.validAgendaDTO();
        VotingSavedDTO votingSavedDTOMock = VotingTemplate.validVotingSavedDTO();
        votingSavedDTOMock.setVote("nao");
        when(associateMapper.to(any())).thenReturn(AssociateTemplate.validAssociateEntity());
        when(agendaMapper.to(any())).thenReturn(AgendaTemplate.validAgendaEntity());

        VotingEntity votingEntity = mapper.to(associateDTOMock, agendaDTOMock, votingSavedDTOMock);
        AssociateEntity associateEntity = votingEntity.getAssociate();
        AgendaEntity agendaEntity = votingEntity.getAgenda();
        assertEquals(associateDTOMock.getId(), associateEntity.getId(),
                "Assertion fail, value invalid");
        assertEquals(associateDTOMock.getName(), associateEntity.getName(),
                "Assertion fail, value invalid");
        assertEquals(agendaDTOMock.getId(), agendaEntity.getId(),
                "Assertion fail, value invalid");
        assertEquals(agendaDTOMock.getTitle(), agendaEntity.getTitle(),
                "Assertion fail, value invalid");
        assertEquals(agendaDTOMock.getDescription(), agendaEntity.getDescription(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When To VotingEntity Is Invalid")
    void testShouldPassWhenToVotingEntityIsInvalid() {
        AssociateDTO associateDTOMock = AssociateTemplate.validAgendaDTO();
        AgendaDTO agendaDTOMock = AgendaTemplate.validAgendaDTO();
        VotingSavedDTO votingSavedDTOMock = VotingTemplate.validVotingSavedDTO();
        votingSavedDTOMock.setVote(null);
        when(associateMapper.to(any())).thenReturn(AssociateTemplate.validAssociateEntity());
        when(agendaMapper.to(any())).thenReturn(AgendaTemplate.validAgendaEntity());

        PocAssembleiaException error = assertThrows(PocAssembleiaException.class, () ->
                        mapper.to(associateDTOMock, agendaDTOMock, votingSavedDTOMock),
                "Assertion fail, Exception not throw");
        assertEquals(PocSicredErrors.VOTE_INVALID.getMessage(), error.getMessage(),
                "Assertion fail, message error invalid");
    }

    @Test
    @DisplayName("Should Pass When From VotingDTO Is Valid")
    void testShouldPassWhenFromVotingDTOIsValid() {
        AgendaDTO agendaDTOMock = AgendaTemplate.validAgendaDTO();
        AgendaEntity agendaEntity = AgendaTemplate.validAgendaEntity();
        VotingEntity votingEntityMock = VotingTemplate.validVotingEntity();
        when(agendaMapper.from(any(AgendaEntity.class))).thenReturn(agendaDTOMock);

        VotingDTO votingDTO = mapper.from(
                List.of(votingEntityMock), List.of(votingEntityMock), agendaEntity);
        AgendaDTO agendaDTO = votingDTO.getAgendaDTO();
        assertEquals(agendaDTOMock.getId(), agendaDTO.getId(),
                "Assertion fail, value invalid");
        assertEquals(agendaDTOMock.getTitle(), agendaDTO.getTitle(),
                "Assertion fail, value invalid");
        assertEquals(agendaDTOMock.getDescription(), agendaDTO.getDescription(),
                "Assertion fail, value invalid");
        assertNotNull(votingDTO.getVoteYes(), "Assertion fail, value invalid");
        assertNotNull(votingDTO.getVoteNo(), "Assertion fail, value invalid");
    }

}

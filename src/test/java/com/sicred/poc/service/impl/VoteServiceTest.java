package com.sicred.poc.service.impl;

import com.sicred.poc.external.dto.AgendaDTO;
import com.sicred.poc.external.dto.VotingDTO;
import com.sicred.poc.external.dto.VotingSavedDTO;
import com.sicred.poc.mapper.AgendaMapper;
import com.sicred.poc.mapper.VotingMapper;
import com.sicred.poc.model.VotingEntity;
import com.sicred.poc.repository.VoteRepository;
import com.sicred.poc.template.AgendaTemplate;
import com.sicred.poc.template.AssociateTemplate;
import com.sicred.poc.template.VotingTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class VoteServiceTest {

    @InjectMocks
    private VoteService service;
    @Mock
    private VoteRepository repository;
    @Mock
    private AssociateService associateService;
    @Mock
    private AgendaService agendaService;
    @Mock
    private VotingMapper mapper;
    @Mock
    private AgendaMapper agendaMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should Pass When VotingDTO In PollResult")
    void testShouldPassWhenVotingDTOInPollResult() {
        AgendaDTO agendaDTOMock = AgendaTemplate.validAgendaDTO();
        when(agendaMapper.to(any())).thenReturn(AgendaTemplate.validAgendaEntity());
        VotingEntity votingEntityMock = VotingTemplate.validVotingEntity();
        when(repository.findByAgendaAndVote(any(),anyString()))
                .thenReturn(List.of(votingEntityMock));
        when(repository.findByAgendaAndVote(any(),anyString()))
                .thenReturn(List.of(votingEntityMock));
        when(mapper.from(any(),any(),any())).thenReturn(VotingTemplate.validVotingDTO());

        VotingDTO votingDTO = service.pollResult(agendaDTOMock);
        assertEquals(agendaDTOMock.getId(), votingDTO.getAgendaDTO().getId(),
                "Assertion fail, value invalid");
        assertEquals(agendaDTOMock.getTitle(), votingDTO.getAgendaDTO().getTitle(),
                "Assertion fail, value invalid");
        assertEquals(agendaDTOMock.getDescription(), votingDTO.getAgendaDTO().getDescription(),
                "Assertion fail, value invalid");
    }

    @Test
    @DisplayName("Should Pass When Voting Is Valid")
    void testShouldPassWhenVotingIsValid() {
        VotingSavedDTO votingSavedDTO = VotingTemplate.validVotingSavedDTO();
        VotingEntity votingEntity = VotingTemplate.validVotingEntity();
        when(associateService.findByName(anyString()))
                .thenReturn(AssociateTemplate.validAgendaDTO());
        when(agendaService.findByTitle(any())).thenReturn(AgendaTemplate.validAgendaDTO());
        when(mapper.to(any(), any(), any())).thenReturn(votingEntity);
        service.voting(votingSavedDTO);
        verify(repository, Mockito.atMostOnce()).save(votingEntity);
    }

}
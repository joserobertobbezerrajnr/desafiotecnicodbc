package com.sicred.poc.service.impl;

import com.sicred.poc.external.dto.AgendaDTO;
import com.sicred.poc.external.dto.AssociateDTO;
import com.sicred.poc.external.dto.VotingDTO;
import com.sicred.poc.external.dto.VotingSavedDTO;
import com.sicred.poc.mapper.AgendaMapper;
import com.sicred.poc.mapper.VotingMapper;
import com.sicred.poc.model.AgendaEntity;
import com.sicred.poc.model.VotingEntity;
import com.sicred.poc.repository.VoteRepository;
import com.sicred.poc.service.IVoteService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class VoteService implements IVoteService {

    private VoteRepository repository;
    private AssociateService associateService;
    private AgendaService agendaService;
    private VotingMapper mapper;
    private AgendaMapper agendaMapper;

    @Override
    @SneakyThrows
    public VotingDTO pollResult(AgendaDTO agendaDTO) {
        AgendaEntity agenda = agendaMapper.to(agendaService.findByTitle(agendaDTO.getTitle()));
        log.info("Agenda " + agenda);
        List<VotingEntity> votesYes = repository.findByAgendaAndVote(agenda, "sim");
        List<VotingEntity> votesNo = repository.findByAgendaAndVote(agenda, "nao");
        return mapper.from(votesYes, votesNo, agenda);
    }

    @Override
    @SneakyThrows
    @Transactional
    public void voting(VotingSavedDTO votingSavedDTO) {
        AssociateDTO associateDTO = associateService.findByName(votingSavedDTO.getAssociateSaveDTO().getName());
        AgendaDTO agendaDTO = agendaService.findByTitle(votingSavedDTO.getAgendaSaveDTO().getTitle());
        VotingEntity votingEntity = mapper.to(associateDTO, agendaDTO, votingSavedDTO);
        log.info("Voting " + votingEntity);
        repository.save(votingEntity);
    }
}

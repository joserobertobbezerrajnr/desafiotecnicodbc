package com.sicred.poc.mapper;

import com.sicred.poc.exception.PocAssembleiaException;
import com.sicred.poc.exception.PocSicredErrors;
import com.sicred.poc.external.dto.AgendaDTO;
import com.sicred.poc.external.dto.AssociateDTO;
import com.sicred.poc.external.dto.VotingDTO;
import com.sicred.poc.external.dto.VotingSavedDTO;
import com.sicred.poc.model.AgendaEntity;
import com.sicred.poc.model.VotingEntity;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class VotingMapper {

    private AssociateMapper associateMapper;
    private AgendaMapper agendaMapper;

    private static final String YES = "sim";
    private static final String NO = "nao";

    public VotingEntity to(AssociateDTO associateDTO, AgendaDTO agendaDTO, VotingSavedDTO votingSavedDTO) {
        return VotingEntity.builder()
                .associate(associateMapper.to(associateDTO))
                .agenda(agendaMapper.to(agendaDTO))
                .vote(checkVote(votingSavedDTO))
                .build();
    }

    public VotingDTO from(List<VotingEntity> votesYes, List<VotingEntity> votesNo, AgendaEntity agenda) {
        return VotingDTO.builder()
                .agendaDTO(agendaMapper.from(agenda))
                .voteYes(String.valueOf(votesYes.size()))
                .voteNo(String.valueOf(votesNo.size()))
                .build();
    }

    @SneakyThrows
    private static String checkVote(VotingSavedDTO votingSavedDTO) {
        if (YES.equals(votingSavedDTO.getVote())) {
            return YES;
        }
        if (NO.equals(votingSavedDTO.getVote())) {
            return NO;
        }
        throw new PocAssembleiaException(PocSicredErrors.VOTE_INVALID);
    }

}

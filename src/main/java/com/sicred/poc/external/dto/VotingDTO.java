package com.sicred.poc.external.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represent a VotingDTO
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VotingDTO {

    private AgendaDTO agendaDTO;
    private String voteYes;
    private String voteNo;

}

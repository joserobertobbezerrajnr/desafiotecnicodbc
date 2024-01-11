package com.sicred.poc.external.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represent a VotingSavedDTO
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VotingSavedDTO {

    private AssociateSaveDTO associateSaveDTO;
    private AgendaSaveDTO agendaSaveDTO;
    private String vote;

}

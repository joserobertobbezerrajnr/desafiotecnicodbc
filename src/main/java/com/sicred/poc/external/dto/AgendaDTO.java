package com.sicred.poc.external.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represent a AgendaDTO
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgendaDTO {

    private Long id;
    private String title;
    private String description;
}

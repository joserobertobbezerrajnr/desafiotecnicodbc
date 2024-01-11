package com.sicred.poc.external.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represent a AgendaSaveDTO
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AgendaSaveDTO {

    private String title;
    private String description;

}

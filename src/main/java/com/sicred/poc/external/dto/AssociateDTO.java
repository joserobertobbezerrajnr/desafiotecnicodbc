package com.sicred.poc.external.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represent a AssociateDTO
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssociateDTO {

    private Long id;
    private String name;

}

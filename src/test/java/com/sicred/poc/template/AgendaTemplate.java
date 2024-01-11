package com.sicred.poc.template;

import com.sicred.poc.external.dto.AgendaDTO;
import com.sicred.poc.external.dto.AgendaSaveDTO;
import com.sicred.poc.model.AgendaEntity;

public class AgendaTemplate {

    public static AgendaSaveDTO validAgendaSaveDTO() {
        return AgendaSaveDTO.builder()
                .title("Titulo da pauta teste")
                .description("Descricao da pauta teste")
                .build();
    }

    public static AgendaDTO validAgendaDTO() {
        return AgendaDTO.builder()
                .id(1L)
                .title("Titulo da pauta teste")
                .description("Descricao da pauta teste")
                .build();
    }

    public static AgendaEntity validAgendaEntity() {
        return AgendaEntity.builder()
                .id(1L)
                .title("Titulo da pauta teste")
                .description("Descricao da pauta teste")
                .build();
    }

}

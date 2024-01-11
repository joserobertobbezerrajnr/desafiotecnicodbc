package com.sicred.poc.mapper;

import com.sicred.poc.exception.PocAssembleiaException;
import com.sicred.poc.exception.PocSicredErrors;
import com.sicred.poc.external.dto.AgendaDTO;
import com.sicred.poc.external.dto.AgendaSaveDTO;
import com.sicred.poc.model.AgendaEntity;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AgendaMapper {

    public AgendaEntity to(AgendaDTO agendaDTO) {
        return AgendaEntity.builder()
                .id(agendaDTO.getId())
                .title(agendaDTO.getTitle())
                .description(agendaDTO.getDescription())
                .build();
    }

    public List<AgendaDTO> from(List<AgendaEntity> agendas) {
        List<AgendaDTO> agendasDTO = new ArrayList<>();
        agendas.forEach(agenda ->
                agendasDTO.add(AgendaDTO.builder()
                        .id(agenda.getId())
                        .title(agenda.getTitle())
                        .description(agenda.getDescription())
                        .build())
        );
        return agendasDTO;
    }

    public AgendaDTO from(AgendaEntity agenda) {
        return AgendaDTO.builder()
                .id(agenda.getId())
                .title(agenda.getTitle())
                .description(agenda.getDescription())
                .build();
    }

    @SneakyThrows
    public AgendaEntity toSave(AgendaSaveDTO agendaDTO) {
        return AgendaEntity.builder()
                .title(agendaDTO.getTitle())
                .description(agendaDTO.getDescription())
                .build();
    }

    @SneakyThrows
    public AgendaEntity toUpdate(AgendaEntity oldAgenda, AgendaDTO newAgendaDTO) {
        return AgendaEntity.builder()
                .id(oldAgenda.getId())
                .title(isDifferent(newAgendaDTO.getTitle(), oldAgenda.getTitle()))
                .description(isDifferent(newAgendaDTO.getDescription(), oldAgenda.getDescription()))
                .build();
    }

    private static String isDifferent(String newValue, String oldValue) {
        if (newValue != null && !newValue.equals(oldValue))
            return isValid(newValue);
        else
            return isValid(oldValue);
    }

    @SneakyThrows
    private static String isValid(String value) {
        if (value.trim().isEmpty()) {
            throw new PocAssembleiaException(PocSicredErrors.VALUE_INVALID);
        }
        return value;
    }
}

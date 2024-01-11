package com.sicred.poc.service;

import com.sicred.poc.external.dto.AgendaDTO;
import com.sicred.poc.external.dto.AgendaSaveDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAgendaService {

    List<AgendaDTO> getAll();
    AgendaDTO findByTitle(String title);
    ResponseEntity<AgendaDTO> save(AgendaSaveDTO agendaDTO);
    AgendaDTO update(AgendaDTO agendaDTO);
    void deleteById(Long id);
}

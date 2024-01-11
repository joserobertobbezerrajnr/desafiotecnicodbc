package com.sicred.poc.service;

import com.sicred.poc.external.dto.AssociateDTO;
import com.sicred.poc.external.dto.AssociateSaveDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAssociateService {

    List<AssociateDTO> getAll();

    AssociateDTO findByName(String name);

    ResponseEntity<AssociateDTO> save(AssociateSaveDTO associateSaveDTO);

    AssociateDTO update(AssociateDTO associateDTO);

    void deleteById(Long id);

}

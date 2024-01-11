package com.sicred.poc.service.impl;

import com.sicred.poc.exception.PocAssembleiaException;
import com.sicred.poc.exception.PocSicredErrors;
import com.sicred.poc.external.dto.AssociateDTO;
import com.sicred.poc.external.dto.AssociateSaveDTO;
import com.sicred.poc.mapper.AssociateMapper;
import com.sicred.poc.model.AssociateEntity;
import com.sicred.poc.repository.AssociateRepository;
import com.sicred.poc.service.IAssociateService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class AssociateService implements IAssociateService {

    private AssociateRepository repository;
    private AssociateMapper mapper;

    @SneakyThrows
    @Override
    public List<AssociateDTO> getAll() {
        List<AssociateEntity> associates = repository.findAll();
        if (associates.isEmpty()) {
            log.error("Associate " + associates);
            throw new PocAssembleiaException(PocSicredErrors.ASSOCIATE_NOT_FOUND);
        }
        return mapper.from(associates);
    }

    @Override
    @SneakyThrows
    public AssociateDTO findByName(String name) {
        checkValue(name);
        log.info("Find By Name Associate " + name);
        AssociateEntity associate = repository.findByName(name).orElseThrow(() ->
                new PocAssembleiaException(PocSicredErrors.ASSOCIATE_NOT_FOUND));
        return mapper.from(associate);
    }

    @Override
    @SneakyThrows
    @Transactional
    public ResponseEntity<AssociateDTO> save(AssociateSaveDTO associateSaveDTO) {
        checkValue(associateSaveDTO.getName());
        Optional<AssociateEntity> existsAssociate = repository.findByName(associateSaveDTO.getName());
        if (existsAssociate.isEmpty()) {
            AssociateEntity associate = mapper.toSave(associateSaveDTO);
            log.info("Save Associate Name " + associate);
            AssociateEntity associateSaved = Optional.of(repository.save(associate))
                    .orElseThrow(() -> new PocAssembleiaException(PocSicredErrors.ASSOCIATE_NOT_SAVED));
            return ResponseEntity.created(URI.create("/associate/save")).body(mapper.from(associateSaved));
        }
        return ResponseEntity.ok(mapper.from(existsAssociate.get()));
    }

    @Override
    @SneakyThrows
    @Transactional
    public AssociateDTO update(AssociateDTO associateDTO) {
        AssociateEntity oldAssociate = repository.findById(associateDTO.getId())
                .orElseThrow(() -> new PocAssembleiaException(PocSicredErrors.ASSOCIATE_NOT_FOUND));
        log.info("Update Associate new value " + associateDTO + " old value " + oldAssociate);
        AssociateEntity associate = mapper.toUpdate(oldAssociate, associateDTO);
        AssociateEntity associateSaved = Optional.of(repository.save(associate))
                .orElseThrow(() -> new PocAssembleiaException(PocSicredErrors.ASSOCIATE_NOT_UPDATED));
        return mapper.from(associateSaved);
    }

    @Override
    @Transactional
    @SneakyThrows
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @SneakyThrows
    private static void checkValue(String value) {
        if (value == null || value.trim().isEmpty()) {
            log.error("Value Invalid " + value);
            throw new PocAssembleiaException(PocSicredErrors.VALUE_INVALID);
        }
    }

}

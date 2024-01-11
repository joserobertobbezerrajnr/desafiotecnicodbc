package com.sicred.poc.mapper;

import com.sicred.poc.exception.PocAssembleiaException;
import com.sicred.poc.exception.PocSicredErrors;
import com.sicred.poc.external.dto.AssociateDTO;
import com.sicred.poc.external.dto.AssociateSaveDTO;
import com.sicred.poc.model.AssociateEntity;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AssociateMapper {

    public AssociateEntity to(AssociateDTO associateDTO) {
        return AssociateEntity.builder()
                .id(associateDTO.getId())
                .name(associateDTO.getName())
                .build();
    }

    public List<AssociateDTO> from(List<AssociateEntity> associates) {
        List<AssociateDTO> associatesDTO = new ArrayList<>();
        associates.forEach(associate ->
                associatesDTO.add(AssociateDTO.builder()
                        .id(associate.getId())
                        .name(associate.getName())
                        .build())
        );
        return associatesDTO;
    }

    public AssociateDTO from(AssociateEntity associate) {
        return AssociateDTO.builder()
                .id(associate.getId())
                .name(associate.getName())
                .build();
    }

    @SneakyThrows
    public AssociateEntity toSave(AssociateSaveDTO associateSaveDTO) {
        return AssociateEntity.builder()
                .name(associateSaveDTO.getName())
                .build();
    }

    @SneakyThrows
    public AssociateEntity toUpdate(AssociateEntity oldAssociate, AssociateDTO newAssociate) {
        return AssociateEntity.builder()
                .id(oldAssociate.getId())
                .name(isDifferent(newAssociate.getName(), oldAssociate.getName()))
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

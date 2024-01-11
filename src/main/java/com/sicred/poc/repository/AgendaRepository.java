package com.sicred.poc.repository;

import com.sicred.poc.model.AgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AgendaRepository extends JpaRepository<AgendaEntity, Long> {
    Optional<AgendaEntity> findByTitle(String title);
}

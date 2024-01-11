package com.sicred.poc.repository;

import com.sicred.poc.model.AssociateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociateRepository extends JpaRepository<AssociateEntity, Long> {

    Optional<AssociateEntity> findByName(String title);

}

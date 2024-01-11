package com.sicred.poc.repository;

import com.sicred.poc.model.AgendaEntity;
import com.sicred.poc.model.VotingEntity;
import com.sicred.poc.model.id.VotingEntityId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoteRepository extends JpaRepository<VotingEntity, VotingEntityId> {

    List<VotingEntity> findByAgendaAndVote(AgendaEntity agenda, String vote);

}

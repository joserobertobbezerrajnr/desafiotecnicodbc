package com.sicred.poc.model;

import com.sicred.poc.model.id.VotingEntityId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Entidade para representar Votacao
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
@ToString@IdClass(VotingEntityId.class)
public class VotingEntity {

    @Id
    @ManyToOne(targetEntity = AssociateEntity.class)
    @JoinColumn(name = "ID_ASSOCIATE", nullable = false)
    private AssociateEntity associate;

    @Id
    @ManyToOne(targetEntity = AgendaEntity.class)
    @JoinColumn(name = "ID_AGENDA", nullable = false)
    private AgendaEntity agenda;

    @Column(nullable = false)
    private String vote;
}



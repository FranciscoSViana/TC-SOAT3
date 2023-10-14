package com.tech.challenge.soat.infra.persistence.jpa.repositories;

import com.tech.challenge.soat.domain.models.ProdutoModel;
import com.tech.challenge.soat.infra.persistence.jpa.entities.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaProdutoRepository extends JpaRepository<ProdutoEntity, UUID> {
}

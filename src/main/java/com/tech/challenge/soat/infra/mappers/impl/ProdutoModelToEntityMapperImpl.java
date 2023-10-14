package com.tech.challenge.soat.infra.mappers.impl;

import com.tech.challenge.soat.domain.models.ProdutoModel;
import com.tech.challenge.soat.infra.mappers.ModelEntityMapper;
import com.tech.challenge.soat.infra.persistence.jpa.entities.ProdutoEntity;
import org.springframework.stereotype.Component;

@Component
public class ProdutoModelToEntityMapperImpl  implements ModelEntityMapper<ProdutoModel, ProdutoEntity> {
    @Override
    public Class<ProdutoEntity> getEntityClass() {
        return ProdutoEntity.class;
    }

    @Override
    public Class<ProdutoModel> getModelClass() {
        return ProdutoModel.class;
    }
}

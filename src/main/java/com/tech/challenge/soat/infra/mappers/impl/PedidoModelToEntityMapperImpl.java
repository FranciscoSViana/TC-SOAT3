package com.tech.challenge.soat.infra.mappers.impl;

import com.tech.challenge.soat.domain.models.PedidoModel;
import com.tech.challenge.soat.infra.mappers.ModelEntityMapper;
import com.tech.challenge.soat.infra.persistence.jpa.entities.PedidoEntity;
import org.springframework.stereotype.Component;

@Component
public class PedidoModelToEntityMapperImpl implements ModelEntityMapper<PedidoModel, PedidoEntity> {
    @Override
    public Class<PedidoEntity> getEntityClass() {
        return PedidoEntity.class;
    }

    @Override
    public Class<PedidoModel> getModelClass() {
        return PedidoModel.class;
    }
}

package com.tech.challenge.soat.infra.mappers.impl;

import com.tech.challenge.soat.domain.models.ClienteModel;
import com.tech.challenge.soat.infra.mappers.ModelEntityMapper;
import com.tech.challenge.soat.infra.persistence.jpa.entities.ClienteEntity;
import org.springframework.stereotype.Component;

@Component
public class ClienteModelToEntityMapperImpl implements ModelEntityMapper<ClienteModel, ClienteEntity> {
    @Override
    public Class<ClienteEntity> getEntityClass() {
        return ClienteEntity.class;
    }

    @Override
    public Class<ClienteModel> getModelClass() {
        return ClienteModel.class;
    }
}

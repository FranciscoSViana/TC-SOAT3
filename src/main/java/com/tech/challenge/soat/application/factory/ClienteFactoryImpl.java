package com.tech.challenge.soat.application.factory;

import com.tech.challenge.soat.adapters.models.in.ClienteRequest;
import com.tech.challenge.soat.domain.constants.I18n;
import com.tech.challenge.soat.domain.exceptions.NegocioException;
import com.tech.challenge.soat.domain.factory.ClienteFactory;
import com.tech.challenge.soat.domain.models.ClienteModel;
import com.tech.challenge.soat.domain.providers.DataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClienteFactoryImpl implements ClienteFactory {

    private final DataProvider dataProvider;


    @Override
    public ClienteModel novo(ClienteRequest clienteRequest) {

        if(isNull(clienteRequest)) {
            throw new NegocioException(I18n.CLIENTE_NAO_PODE_SER_NULO);
        }

        return ClienteModel.builder()
                .id(UUID.randomUUID())
                .dataHoraCriacao(dataProvider.obterDataHoraAtual())
                .situacao(Boolean.TRUE)
                .nome(clienteRequest.getNome())
                .cpf(clienteRequest.getCpf())
                .email(clienteRequest.getEmail())
                .telefone(clienteRequest.getTelefone())
                .build();
    }
}

package com.tech.challenge.soat.core.applications.factory;

import com.tech.challenge.soat.adapters.driver.v1.model.request.ClienteRequest;
import com.tech.challenge.soat.domain.models.ClienteModel;
import com.tech.challenge.soat.core.exception.NegocioException;
import com.tech.challenge.soat.shared.util.provider.DataProvider;
import com.tech.challenge.soat.shared.util.provider.UUIDProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ClienteFactoryImpl implements ClienteFactory {

    private final UUIDProvider uuidProvider;

    private final DataProvider dataProvider;
    public static final String CLIENTE_NAO_PODE_SER_NULO = "Cliente não pode ser nulo.";


    @Override
    public ClienteModel novo(ClienteRequest clienteRequest) {

        if(isNull(clienteRequest)) {
            throw new NegocioException(CLIENTE_NAO_PODE_SER_NULO);
        }

        return ClienteModel.builder()
                .id(uuidProvider.geradorUUID())
                .dataHoraCriacao(dataProvider.obterDataHoraAtual())
                .situacao(Boolean.TRUE)
                .nome(clienteRequest.getNome())
                .cpf(clienteRequest.getCpf())
                .email(clienteRequest.getEmail())
                .telefone(clienteRequest.getTelefone())
                .build();
    }
}

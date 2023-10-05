package com.tech.challenge.soat.core.applications.factory;

import com.tech.challenge.soat.adapters.driver.v1.model.input.ClienteInput;
import com.tech.challenge.soat.core.domain.Cliente;
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

    private UUIDProvider uuidProvider;
    private DataProvider dataProvider;
    public static final String CLIENTE_NAO_PODE_SER_NULO = "Cliente não pode ser nulo.";


    @Override
    public Cliente novo(ClienteInput clienteInput) {

        if(isNull(clienteInput)) {
            throw new NegocioException(CLIENTE_NAO_PODE_SER_NULO);
        }

        return Cliente.builder()
                .id(uuidProvider.geradorUUID())
                .dataHoraCriacao(dataProvider.obterDataHoraAtual())
                .situacao(Boolean.TRUE)
                .nome(clienteInput.getNome())
                .cpf(clienteInput.getCpf())
                .email(clienteInput.getEmail())
                .build();
    }
}

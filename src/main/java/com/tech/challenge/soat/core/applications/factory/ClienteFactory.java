package com.tech.challenge.soat.core.applications.factory;

import com.tech.challenge.soat.adapters.driver.v1.model.input.ClienteInput;
import com.tech.challenge.soat.core.domain.Cliente;

@FunctionalInterface
public interface ClienteFactory {
    Cliente novo(ClienteInput clienteInput);
}

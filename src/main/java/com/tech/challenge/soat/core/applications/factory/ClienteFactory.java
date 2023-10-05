package com.tech.challenge.soat.core.applications.factory;

import com.tech.challenge.soat.adapters.driver.v1.model.request.ClienteRequest;
import com.tech.challenge.soat.core.domain.Cliente;

@FunctionalInterface
public interface ClienteFactory {
    Cliente novo(ClienteRequest clienteRequest);
}

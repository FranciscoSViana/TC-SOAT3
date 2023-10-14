package com.tech.challenge.soat.core.applications.factory;

import com.tech.challenge.soat.adapters.driver.v1.model.request.ClienteRequest;
import com.tech.challenge.soat.domain.models.ClienteModel;

@FunctionalInterface
public interface ClienteFactory {
    ClienteModel novo(ClienteRequest clienteRequest);
}

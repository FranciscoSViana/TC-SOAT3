package com.tech.challenge.soat.domain.factory;

import com.tech.challenge.soat.adapters.models.in.ClienteRequest;
import com.tech.challenge.soat.domain.models.ClienteModel;

@FunctionalInterface
public interface ClienteFactory {
    ClienteModel novo(ClienteRequest clienteRequest);
}

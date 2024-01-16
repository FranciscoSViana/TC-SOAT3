package com.tech.challenge.soat.domain.services;
@FunctionalInterface
public interface WebhookMPService {
    void integracaoMP(String payload);
}

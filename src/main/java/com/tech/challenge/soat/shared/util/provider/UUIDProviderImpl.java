package com.tech.challenge.soat.shared.util.provider;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDProviderImpl implements UUIDProvider {

    @Override
    public UUID geradorUUID() {return UUID.randomUUID();}


}

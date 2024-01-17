package com.tech.challenge.soat.adapters.controllers;

import com.tech.challenge.soat.domain.services.WebhookMPService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class WebhookMPController {

    private final WebhookMPService service;

    @PostMapping("/integracao")
    public void integracaoMP(@RequestBody String payload) {

        service.integracaoMP(payload);

    }


}

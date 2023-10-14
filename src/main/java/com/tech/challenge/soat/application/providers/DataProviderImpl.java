package com.tech.challenge.soat.application.providers;

import com.tech.challenge.soat.domain.providers.DataProvider;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataProviderImpl implements DataProvider {
    @Override
    public LocalDate obterDataAtutal() {
        return LocalDate.now();
    }

    @Override
    public LocalDateTime obterDataHoraAtual() {
        return LocalDateTime.now();
    }
}

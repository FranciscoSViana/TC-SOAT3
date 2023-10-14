package com.tech.challenge.soat.domain.providers;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DataProvider {

    LocalDate obterDataAtutal();

    LocalDateTime obterDataHoraAtual();

}

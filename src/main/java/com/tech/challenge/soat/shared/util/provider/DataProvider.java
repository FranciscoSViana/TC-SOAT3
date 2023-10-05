package com.tech.challenge.soat.shared.util.provider;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface DataProvider {

    LocalDate obterDataAtutal();

    LocalDateTime obterDataHoraAtual();

}

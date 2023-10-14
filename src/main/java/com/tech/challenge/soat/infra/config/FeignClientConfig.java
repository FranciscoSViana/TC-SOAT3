package com.tech.challenge.soat.infra.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

import static com.tech.challenge.soat.SoatApplication.PACKAGE;

@Configuration
@EnableFeignClients(PACKAGE)
public class FeignClientConfig {
}

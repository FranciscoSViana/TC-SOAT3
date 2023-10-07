package com.tech.challenge.soat.shared.util;

import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class ImagemUtil {

    public byte[] decodeBase64(String base64) {
        return Base64.getDecoder().decode(base64);
    }

    public String encodeBase64(byte[] imagem) {
        return Base64.getEncoder().encodeToString(imagem);
    }
}

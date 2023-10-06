package com.tech.challenge.soat.core.util;

import com.tech.challenge.soat.core.enumerator.CategoriaEnum;
import org.springframework.stereotype.Component;

@Component
public class CategoriaUtil {

    public CategoriaEnum converterStringToCategoriaEnum(String categoriaInput) {
        for (CategoriaEnum categoria : CategoriaEnum.values()) {
            if (categoria.getDescricao().equalsIgnoreCase(categoriaInput)) {
                return categoria;
            }
        }
        throw new IllegalArgumentException("Categoria inválida: " + categoriaInput);
    }
}

package com.tech.challenge.soat.core.enumerator;

public enum CategoriaEnum {

    LANCHE("Lanche"), ACOMPANHAMENTO("Acompanhamento"), BEBIDA("Bebida"), SOBREMESA("Sobremesa");

    private String descricao;

    CategoriaEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}

package com.logistica.model;

public enum StatusEntrega {
    EM_ROTA("Em Rota"),
    ENTREGUE("Entregue"),
    ATRASADA("Atrasada");

    private final String descricao;

    StatusEntrega(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
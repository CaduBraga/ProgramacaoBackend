package com.logistica.model;

import java.time.LocalDateTime;

public class HistoricoEntrega {
    private Long id;
    private Long entregaId;
    private Entrega entrega;
    private LocalDateTime dataEvento;
    private String descricao;

    public HistoricoEntrega() {
        this.dataEvento = LocalDateTime.now();
    }

    public HistoricoEntrega(Long entregaId, String descricao) {
        this();
        this.entregaId = entregaId;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getEntregaId() {
        return entregaId;
    }

    public void setEntregaId(Long entregaId) {
        this.entregaId = entregaId;
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(LocalDateTime dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "HistoricoEntrega{" +
                "id=" + id +
                ", entregaId=" + entregaId +
                ", dataEvento=" + dataEvento +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
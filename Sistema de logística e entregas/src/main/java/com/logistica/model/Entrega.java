package com.logistica.model;

import java.time.LocalDateTime;

public class Entrega {
    private Long id;
    private Long pedidoId;
    private Long motoristaId;
    private Pedido pedido;
    private Motorista motorista;
    private LocalDateTime dataSaida;
    private LocalDateTime dataEntrega;
    private StatusEntrega status;

    public Entrega() {
        this.status = StatusEntrega.EM_ROTA;
    }

    public Entrega(Long pedidoId, Long motoristaId) {
        this();
        this.pedidoId = pedidoId;
        this.motoristaId = motoristaId;
        this.dataSaida = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Long getMotoristaId() {
        return motoristaId;
    }

    public void setMotoristaId(Long motoristaId) {
        this.motoristaId = motoristaId;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public LocalDateTime getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(LocalDateTime dataSaida) {
        this.dataSaida = dataSaida;
    }

    public LocalDateTime getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDateTime dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public StatusEntrega getStatus() {
        return status;
    }

    public void setStatus(StatusEntrega status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Entrega{" +
                "id=" + id +
                ", pedidoId=" + pedidoId +
                ", motoristaId=" + motoristaId +
                ", dataSaida=" + dataSaida +
                ", dataEntrega=" + dataEntrega +
                ", status=" + status +
                '}';
    }
}
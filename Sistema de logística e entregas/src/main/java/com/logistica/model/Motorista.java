package com.logistica.model;

import java.time.LocalDateTime;

public class Motorista {
    private Long id;
    private String nome;
    private String cnh;
    private String veiculo;
    private String cidadeBase;
    private LocalDateTime dataCadastro;

    public Motorista() {
        this.dataCadastro = LocalDateTime.now();
    }

    public Motorista(String nome, String cnh, String veiculo, String cidadeBase) {
        this();
        this.nome = nome;
        this.cnh = cnh;
        this.veiculo = veiculo;
        this.cidadeBase = cidadeBase;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(String veiculo) {
        this.veiculo = veiculo;
    }

    public String getCidadeBase() {
        return cidadeBase;
    }

    public void setCidadeBase(String cidadeBase) {
        this.cidadeBase = cidadeBase;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    @Override
    public String toString() {
        return "Motorista{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cnh='" + cnh + '\'' +
                ", veiculo='" + veiculo + '\'' +
                ", cidadeBase='" + cidadeBase + '\'' +
                ", dataCadastro=" + dataCadastro +
                '}';
    }
}
package com.logistica.service;

import com.logistica.dao.MotoristaDAO;
import com.logistica.model.Motorista;

import java.sql.SQLException;
import java.util.List;

public class MotoristaService {
    
    private MotoristaDAO motoristaDAO;
    
    public MotoristaService() {
        this.motoristaDAO = new MotoristaDAO();
    }
    
    public Long cadastrarMotorista(Motorista motorista) throws SQLException {
        if (motorista.getNome() == null || motorista.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (motorista.getCnh() == null || motorista.getCnh().trim().isEmpty()) {
            throw new IllegalArgumentException("CNH é obrigatória");
        }
        if (motorista.getVeiculo() == null || motorista.getVeiculo().trim().isEmpty()) {
            throw new IllegalArgumentException("Veículo é obrigatório");
        }
        if (motorista.getCidadeBase() == null || motorista.getCidadeBase().trim().isEmpty()) {
            throw new IllegalArgumentException("Cidade base é obrigatória");
        }
        
        Motorista motoristaExistente = motoristaDAO.buscarPorCnh(motorista.getCnh());
        if (motoristaExistente != null) {
            throw new IllegalArgumentException("Já existe um motorista com esta CNH");
        }
        
        return motoristaDAO.inserir(motorista);
    }
    
    public Motorista buscarMotoristaPorId(Long id) throws SQLException {
        return motoristaDAO.buscarPorId(id);
    }
    
    public Motorista buscarMotoristaPorCnh(String cnh) throws SQLException {
        return motoristaDAO.buscarPorCnh(cnh);
    }
    
    public List<Motorista> listarTodosMotoristas() throws SQLException {
        return motoristaDAO.listarTodos();
    }
    
    public List<Motorista> listarMotoristasPorCidadeBase(String cidadeBase) throws SQLException {
        return motoristaDAO.listarPorCidadeBase(cidadeBase);
    }
    
    public boolean atualizarMotorista(Motorista motorista) throws SQLException {
        if (motorista.getId() == null) {
            throw new IllegalArgumentException("ID do motorista é obrigatório para atualização");
        }
        
        Motorista motoristaExistente = motoristaDAO.buscarPorId(motorista.getId());
        if (motoristaExistente == null) {
            throw new IllegalArgumentException("Motorista não encontrado");
        }
        
        Motorista motoristaComMesmaCnh = motoristaDAO.buscarPorCnh(motorista.getCnh());
        if (motoristaComMesmaCnh != null && !motoristaComMesmaCnh.getId().equals(motorista.getId())) {
            throw new IllegalArgumentException("Já existe outro motorista com esta CNH");
        }
        
        return motoristaDAO.atualizar(motorista);
    }
    
    public boolean excluirMotorista(Long id) throws SQLException {
        Motorista motorista = motoristaDAO.buscarPorId(id);
        if (motorista == null) {
            throw new IllegalArgumentException("Motorista não encontrado");
        }
        
        if (motoristaDAO.temEntregasAssociadas(id)) {
            throw new IllegalArgumentException("Não é possível excluir motorista com entregas associadas");
        }
        
        return motoristaDAO.excluir(id);
    }
}

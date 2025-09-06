package com.logistica.service;

import com.logistica.dao.ClienteDAO;
import com.logistica.model.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteService {
    
    private ClienteDAO clienteDAO;
    
    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }
    
    public Long cadastrarCliente(Cliente cliente) throws SQLException {
        if (cliente.getNome() == null || cliente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório");
        }
        if (cliente.getCpfCnpj() == null || cliente.getCpfCnpj().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF/CNPJ é obrigatório");
        }
        if (cliente.getEndereco() == null || cliente.getEndereco().trim().isEmpty()) {
            throw new IllegalArgumentException("Endereço é obrigatório");
        }
        if (cliente.getCidade() == null || cliente.getCidade().trim().isEmpty()) {
            throw new IllegalArgumentException("Cidade é obrigatória");
        }
        if (cliente.getEstado() == null || cliente.getEstado().trim().isEmpty()) {
            throw new IllegalArgumentException("Estado é obrigatório");
        }
        
        Cliente clienteExistente = clienteDAO.buscarPorCpfCnpj(cliente.getCpfCnpj());
        if (clienteExistente != null) {
            throw new IllegalArgumentException("Já existe um cliente com este CPF/CNPJ");
        }
        
        return clienteDAO.inserir(cliente);
    }
    
    public Cliente buscarClientePorId(Long id) throws SQLException {
        return clienteDAO.buscarPorId(id);
    }
    
    public Cliente buscarClientePorCpfCnpj(String cpfCnpj) throws SQLException {
        return clienteDAO.buscarPorCpfCnpj(cpfCnpj);
    }
    
    public List<Cliente> listarTodosClientes() throws SQLException {
        return clienteDAO.listarTodos();
    }
    
    public List<Cliente> listarClientesPorEstado(String estado) throws SQLException {
        return clienteDAO.listarPorEstado(estado);
    }
    
    public boolean atualizarCliente(Cliente cliente) throws SQLException {
        if (cliente.getId() == null) {
            throw new IllegalArgumentException("ID do cliente é obrigatório para atualização");
        }
        
        Cliente clienteExistente = clienteDAO.buscarPorId(cliente.getId());
        if (clienteExistente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        
        Cliente clienteComMesmoCpfCnpj = clienteDAO.buscarPorCpfCnpj(cliente.getCpfCnpj());
        if (clienteComMesmoCpfCnpj != null && !clienteComMesmoCpfCnpj.getId().equals(cliente.getId())) {
            throw new IllegalArgumentException("Já existe outro cliente com este CPF/CNPJ");
        }
        
        return clienteDAO.atualizar(cliente);
    }
    
    public boolean excluirCliente(Long id) throws SQLException {
        Cliente cliente = clienteDAO.buscarPorId(id);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        
        if (clienteDAO.temPedidosAssociados(id)) {
            throw new IllegalArgumentException("Não é possível excluir cliente com pedidos associados");
        }
        
        return clienteDAO.excluir(id);
    }
}

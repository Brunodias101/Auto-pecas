package com.example.Autopecas.Cliente.Service;

import com.example.Autopecas.Cliente.Controller.Request.ClientePostRequest;
import com.example.Autopecas.Cliente.Controller.Request.ClientePutRequest;
import com.example.Autopecas.Cliente.Model.ClienteModel;
import com.example.Autopecas.Cliente.Repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<ClienteModel> buscarTodosClientes() {
        return clienteRepository.findAll();
    }

    public ClienteModel buscarClientePorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente informado por Id não encontrado"));
    }

    public ClienteModel salvarCliente(ClientePostRequest clienteRequest) {
        ClienteModel cliente = new ClienteModel();
        cliente.setNome(clienteRequest.getNome());
        cliente.setTelefone(clienteRequest.getTelefone());
        cliente.setEmail(clienteRequest.getEmail());

        return clienteRepository.save(cliente);
    }

    public ClienteModel atualizarCliente(Long id, ClientePutRequest clienteRequest) {
        ClienteModel cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente informado por Id não encontrado"));
        cliente.setNome(clienteRequest.getNome());
        cliente.setTelefone(clienteRequest.getTelefone());
        cliente.setEmail(clienteRequest.getEmail());

        return clienteRepository.save(cliente);
    }

    public void excluirCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}


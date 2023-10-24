package com.example.Autopecas.Cliente.Model;

import com.example.Autopecas.Cliente.Controller.Request.ClientePostRequest;

import javax.persistence.*;

@Entity(name = "cliente")
public class ClienteModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCliente;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String email;

    public ClienteModel(){

    }
    public ClienteModel(Long id_cliente, String nome, String telefone, String email) {
        this.idCliente = id_cliente;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
    }

    public ClientePostRequest toClientePostRequest() {
        ClientePostRequest request = new ClientePostRequest();
        request.setNome(this.nome);
        request.setTelefone(this.telefone);
        request.setEmail(this.email);
        return request;
    }

    public ClienteModel toClienteModel(Long id) {
        ClienteModel cliente = new ClienteModel();
        cliente.setIdCliente(id);
        cliente.setNome(this.nome);
        cliente.setTelefone(this.telefone);
        cliente.setEmail(this.email);
        return cliente;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



}



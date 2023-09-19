package com.example.Autopecas.Produto.Controller.Request;

import com.example.Autopecas.Fornecedor.Model.FornecedorModel;

import java.math.BigDecimal;

public class ProdutoPutRequest {

    private Long idProduto;

    private String descricao;

    private BigDecimal valorVenda;

    private int quantidade;

    private FornecedorModel fornecedorModel;

    public Long getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Long idProduto) {
        this.idProduto = idProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValorVenda() {
        return valorVenda;
    }

    public void setValorVenda(BigDecimal valorVenda) {
        this.valorVenda = valorVenda;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public FornecedorModel getFornecedorModel() {
        return fornecedorModel;
    }

    public void setFornecedorModel(FornecedorModel fornecedorModel) {
        this.fornecedorModel = fornecedorModel;
    }
}

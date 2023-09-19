package com.example.Autopecas.Produto.Model;

import com.example.Autopecas.Fornecedor.Model.FornecedorModel;
import com.example.Autopecas.Produto.Controller.Request.ProdutoPostRequest;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name="produto")
public class ProdutoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_produto")
    private Long idProduto;

    @Column(nullable = false)
    private String descricao;

    @Column(name = "valor_venda", nullable = false)
    private BigDecimal valorVenda;

    @Column(nullable = false)
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "id_fornecedor")
    private FornecedorModel fornecedor;

    public ProdutoModel() {
    }

    public ProdutoModel(String descricao, BigDecimal valorVenda, int quantidade, FornecedorModel fornecedor) {
        this.descricao = descricao;
        this.valorVenda = valorVenda;
        this.quantidade = quantidade;
        this.fornecedor = fornecedor;
    }

    public ProdutoPostRequest toProdutoPostRequest(){
        ProdutoPostRequest request = new ProdutoPostRequest();
        request.setDescricao(this.descricao);
        request.setValorVenda(this.valorVenda);
        request.setQuantidade(this.quantidade);
        request.setFornecedor(this.fornecedor);
        return request;
    }

    public Long getIdProduto(){
        return idProduto;
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

    public FornecedorModel getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(FornecedorModel fornecedor) {
        this.fornecedor = fornecedor;
    }

}

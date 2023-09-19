package com.example.Autopecas.FunctionsConversao;

import com.example.Autopecas.Cliente.Controller.Request.ClientePutRequest;
import com.example.Autopecas.Cliente.Model.ClienteModel;
import com.example.Autopecas.Fornecedor.Controller.Request.FornecedorPutRequest;
import com.example.Autopecas.Fornecedor.Model.FornecedorModel;
import com.example.Autopecas.Produto.Controller.Request.ProdutoPutRequest;
import com.example.Autopecas.Produto.Model.ProdutoModel;
import org.springframework.stereotype.Component;

@Component
public class ToPutRequestConverter {

    public ClientePutRequest convert(ClienteModel clienteModel) {
        ClientePutRequest clientePutRequest = new ClientePutRequest();
        clientePutRequest.setNome(clienteModel.getNome());
        clientePutRequest.setTelefone(clienteModel.getTelefone());
        clientePutRequest.setEmail(clienteModel.getEmail());
        return clientePutRequest;
    }

    public FornecedorPutRequest convert(FornecedorModel fornecedorModel){
        FornecedorPutRequest fornecedorPutRequest = new FornecedorPutRequest();
        fornecedorPutRequest.setNome(fornecedorModel.getNome());
        fornecedorPutRequest.setValorCompra(fornecedorModel.getValorCompra());
        fornecedorPutRequest.setAreaProduto(fornecedorModel.getAreaProduto());
        return fornecedorPutRequest;
    }

    public ProdutoPutRequest convert(ProdutoModel produtoModel){
        ProdutoPutRequest produtoPutRequest = new ProdutoPutRequest();
        produtoPutRequest.setDescricao(produtoModel.getDescricao());
        produtoPutRequest.setValorVenda(produtoModel.getValorVenda());
        produtoPutRequest.setQuantidade(produtoModel.getQuantidade());
        produtoPutRequest.setFornecedorModel(produtoModel.getFornecedor());

        return produtoPutRequest;
    }
}

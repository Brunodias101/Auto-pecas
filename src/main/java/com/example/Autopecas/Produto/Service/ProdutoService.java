package com.example.Autopecas.Produto.Service;

import com.example.Autopecas.Produto.Controller.Request.ProdutoPostRequest;
import com.example.Autopecas.Produto.Controller.Request.ProdutoPutRequest;
import com.example.Autopecas.Produto.Model.ProdutoModel;
import com.example.Autopecas.Produto.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<ProdutoModel> buscarTodosProdutos(){
        return produtoRepository.findAll();
    }

    public ProdutoModel buscarProdutoPorId(Long id) {
        return  produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente informado por Id não encontrado"));
    }

    public ProdutoModel salvarProduto(ProdutoPostRequest produtoPostRequest) {
        ProdutoModel produto = new ProdutoModel();
        produto.setDescricao(produtoPostRequest.getDescricao());
        produto.setValorVenda(produtoPostRequest.getValorVenda());
        produto.setQuantidade(produtoPostRequest.getQuantidade());
        produto.setFornecedor(produtoPostRequest.getFornecedor());

        return produtoRepository.save(produto);
    }

    public ProdutoModel atualizarProduto(Long id, ProdutoPutRequest produtoPutRequest){
        ProdutoModel produtoModel = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto informado por Id não encontrado"));
        produtoModel.setDescricao(produtoPutRequest.getDescricao());
        produtoModel.setValorVenda(produtoPutRequest.getValorVenda());
        produtoModel.setQuantidade(produtoPutRequest.getQuantidade());
        produtoModel.setFornecedor(produtoPutRequest.getFornecedorModel());

        return produtoRepository.save(produtoModel);
    }

    public void excluirProduto(Long id) {
        produtoRepository.deleteById(id);
    }
}

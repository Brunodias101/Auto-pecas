package com.example.Autopecas.Produto.Controller;

import com.example.Autopecas.FunctionsConversao.ToPutRequestConverter;
import com.example.Autopecas.Produto.Controller.Request.ProdutoPutRequest;
import com.example.Autopecas.Produto.Model.ProdutoModel;
import com.example.Autopecas.Produto.Service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ToPutRequestConverter produtoModelToPutRequestConverter;

    @GetMapping
    public List<ProdutoModel> buscarTodosClientes() {
        return produtoService.buscarTodosProdutos();
    }

    @GetMapping("/{id}")
    public ProdutoModel buscarProdutoPorId(@PathVariable Long id) {
        return produtoService.buscarProdutoPorId(id);
    }

    @PostMapping
    public ProdutoModel salvarProduto(@RequestBody ProdutoModel produto) {
        return produtoService.salvarProduto(produto.toProdutoPostRequest());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarProduto(@PathVariable Long id, @RequestBody ProdutoModel produtoModel) {
        ProdutoPutRequest produtoPutRequest = produtoModelToPutRequestConverter.convert(produtoModel);
        produtoService.atualizarProduto(id, produtoPutRequest);
    }

    @DeleteMapping("/{id}")
    public void excluirProduto(@PathVariable Long id) {
        produtoService.excluirProduto(id);
    }

}

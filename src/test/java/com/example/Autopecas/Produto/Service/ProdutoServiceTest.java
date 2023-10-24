package com.example.Autopecas.Produto.Service;

import com.example.Autopecas.AplicationConfigTest;
import com.example.Autopecas.Fornecedor.Model.FornecedorModel;
import com.example.Autopecas.Produto.Controller.Request.ProdutoPostRequest;
import com.example.Autopecas.Produto.Controller.Request.ProdutoPutRequest;
import com.example.Autopecas.Produto.Model.ProdutoModel;
import com.example.Autopecas.Produto.Repository.ProdutoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DisplayName("ProdutoServiceTest")
public class ProdutoServiceTest extends AplicationConfigTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    void buscarTodosProdutos_deveRetornarListaVazia_quandoNaoExistemClientes() {

        Mockito.when(produtoRepository.findAll()).thenReturn(Collections.emptyList());

        List<ProdutoModel> produtos = produtoService.buscarTodosProdutos();

        assertTrue(produtos.isEmpty());
    }

    @Test
    public void buscarProdutoPorId_deveRetornarProdutoQuandoExistir() {
        // given
        Long id = 1L;

        FornecedorModel fornecedorModel = new FornecedorModel();
        fornecedorModel.setIdFornecedor(id);
        fornecedorModel.setNome("Fornecedor de Teste");
        fornecedorModel.setAreaProduto("Area de teste");
        fornecedorModel.setValorCompra(BigDecimal.valueOf(11));

        ProdutoModel produtoMock = new ProdutoModel();
        produtoMock.setIdProduto(id);
        produtoMock.setDescricao("Cliente de Teste");
        produtoMock.setValorVenda(BigDecimal.valueOf(100));
        produtoMock.setQuantidade(5);
        produtoMock.setFornecedor(fornecedorModel);

        Mockito.when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoMock));

        // when
        ProdutoModel produtoEncontrado = produtoService.buscarProdutoPorId(id);

        // then
        Assertions.assertEquals(produtoMock.getIdProduto(), produtoEncontrado.getIdProduto());
        Assertions.assertEquals(produtoMock.getDescricao(), produtoEncontrado.getDescricao());
        Assertions.assertEquals(produtoMock.getValorVenda(), produtoEncontrado.getValorVenda());
        Assertions.assertEquals(produtoMock.getQuantidade(), produtoEncontrado.getQuantidade());
        Assertions.assertEquals(produtoMock.getFornecedor(), produtoEncontrado.getFornecedor());


        Mockito.verify(produtoRepository, Mockito.times(1)).findById(id);
    }

    @Test
    public void salvarProduto_deveSalvarProdutoNoRepositorio() {
        // given
        Long id = 1L;
        FornecedorModel fornecedorModel = new FornecedorModel();
        fornecedorModel.setIdFornecedor(id);
        fornecedorModel.setNome("Teste");
        fornecedorModel.setAreaProduto("Teste");
        fornecedorModel.setValorCompra(BigDecimal.valueOf(11));

        ProdutoPostRequest produtoRequest = new ProdutoPostRequest("teste",BigDecimal.valueOf(11),12,fornecedorModel);
        ProdutoModel produtoSalvo = new ProdutoModel("teste", BigDecimal.valueOf(11),12,fornecedorModel);

        Mockito.when(produtoRepository.save(Mockito.any(ProdutoModel.class))).thenReturn(produtoSalvo);

        // when
        ProdutoModel produtoRetornado = produtoService.salvarProduto(produtoRequest);

        // then
        Assertions.assertEquals(produtoSalvo, produtoRetornado);
        Mockito.verify(produtoRepository, Mockito.times(1)).save(Mockito.any(ProdutoModel.class));
    }

    @Test
    public void atualizarProduto_deveAtualizarProdutoExistente() {
        // given
        Long id = 1L;
        ProdutoPutRequest produtoPutRequest = new ProdutoPutRequest();
        produtoPutRequest.setDescricao("Teste");
        produtoPutRequest.setQuantidade(12);
        produtoPutRequest.setValorVenda(BigDecimal.valueOf(11));

        FornecedorModel fornecedorTeste = new FornecedorModel();
        fornecedorTeste.setIdFornecedor(id);
        fornecedorTeste.setNome("Teste");
        fornecedorTeste.setAreaProduto("Teste");
        fornecedorTeste.setValorCompra(BigDecimal.valueOf(11));

        ProdutoModel produtoAntigo = new ProdutoModel();
        produtoAntigo.setIdProduto(id);
        produtoAntigo.setDescricao("desc antiga");
        produtoAntigo.setQuantidade(10);
        produtoAntigo.setValorVenda(BigDecimal.valueOf(11));

        ProdutoModel produtoAtualizado = new ProdutoModel();
        produtoAtualizado.setIdProduto(id);
        produtoAtualizado.setDescricao(produtoPutRequest.getDescricao());
        produtoAtualizado.setQuantidade(produtoPutRequest.getQuantidade());
        produtoAtualizado.setValorVenda(produtoPutRequest.getValorVenda());
        produtoAtualizado.setFornecedor(produtoPutRequest.getFornecedorModel());


        when(produtoRepository.findById(id)).thenReturn(Optional.of(produtoAntigo));
        when(produtoRepository.save(Mockito.any(ProdutoModel.class))).thenReturn(produtoAtualizado
        );

        // when
        ProdutoModel produtoRetornado = produtoService.atualizarProduto(id, produtoPutRequest);

        // then
        Assertions.assertEquals(produtoAtualizado.getIdProduto(), produtoRetornado.getIdProduto());
        Assertions.assertEquals(produtoAtualizado.getDescricao(), produtoRetornado.getDescricao());
        Assertions.assertEquals(produtoAtualizado.getQuantidade(), produtoRetornado.getQuantidade());
        Assertions.assertEquals(produtoAtualizado.getValorVenda(), produtoRetornado.getValorVenda());
        Assertions.assertEquals(produtoAtualizado.getFornecedor(), produtoRetornado.getFornecedor());

        Mockito.verify(produtoRepository, Mockito.times(1)).findById(id);
        Mockito.verify(produtoRepository, Mockito.times(1)).save(Mockito.any(ProdutoModel.class));
    }

    @Test
    public void excluirFornecedor_deveExcluirFornecedorExistente() {
        // given
        Long id = 1L;

        // when
        produtoService.excluirProduto(id);

        // then
        Mockito.verify(produtoRepository, Mockito.times(1)).deleteById(id);
    }
}

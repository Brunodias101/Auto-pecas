package com.example.Autopecas.Fornecedor.Service;

import com.example.Autopecas.Fornecedor.Controller.Request.FornecedorPostRequest;
import com.example.Autopecas.Fornecedor.Controller.Request.FornecedorPutRequest;
import com.example.Autopecas.Fornecedor.Model.FornecedorModel;
import com.example.Autopecas.Fornecedor.Repository.FornecedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<FornecedorModel> buscarTodosFornecedores() {
        return fornecedorRepository.findAll();
    }

    public FornecedorModel buscarFornecedorPorId(Long id) {
        return fornecedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Fornecedor informado por Id não encontrado"));
    }

    public FornecedorModel salvarFornecedor(FornecedorPostRequest fornecedorRequest) {
        FornecedorModel fornecedor = new FornecedorModel();
        fornecedor.setNome(fornecedorRequest.getNome());
        fornecedor.setValorCompra(fornecedorRequest.getValorCompra());
        fornecedor.setAreaProduto(fornecedorRequest.getAreaProduto());
        return fornecedorRepository.save(fornecedor);
    }

    public FornecedorModel atualizarFornecedor(Long id, FornecedorPutRequest fornecedorPutRequest){
        FornecedorModel fornecedorModel = fornecedorRepository.findById(id).orElseThrow(() -> new RuntimeException("Fornecedor informado por Id não encontrado"));
        fornecedorModel.setNome(fornecedorPutRequest.getNome());
        fornecedorModel.setValorCompra(fornecedorPutRequest.getValorCompra());
        fornecedorModel.setAreaProduto(fornecedorPutRequest.getAreaProduto());
        return fornecedorRepository.save(fornecedorModel);
    }

    public void excluirFornecedor(Long id ){
        fornecedorRepository.deleteById(id);
    }

}

package com.example.Autopecas.Cliente.Service;

import com.example.Autopecas.AplicationConfigTest;
import com.example.Autopecas.Cliente.Controller.Request.ClientePostRequest;
import com.example.Autopecas.Cliente.Controller.Request.ClientePutRequest;
import com.example.Autopecas.Cliente.Model.ClienteModel;
import com.example.Autopecas.Cliente.Repository.ClienteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@DisplayName("ClienteServiceTest")
public class ClienteServiceTest extends AplicationConfigTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void buscarTodosClientes_deveRetornarListaVazia_quandoNaoExistemClientes() {

        Mockito.when(clienteRepository.findAll()).thenReturn(Collections.emptyList());

        List<ClienteModel> clientes = clienteService.buscarTodosClientes();

        assertTrue(clientes.isEmpty());
    }

    @Test
    public void buscarClientePorId_deveRetornarClienteQuandoExistir() {
        // given
        Long id = 1L;
        ClienteModel clienteMock = new ClienteModel();
        clienteMock.setIdCliente(id);
        clienteMock.setNome("Cliente de Teste");
        clienteMock.setTelefone("1111111111");
        clienteMock.setEmail("cliente@teste.com");

        Mockito.when(clienteRepository.findById(id)).thenReturn(Optional.of(clienteMock));

        // when
        ClienteModel clienteEncontrado = clienteService.buscarClientePorId(id);

        // then
        Assertions.assertEquals(clienteMock.getIdCliente(), clienteEncontrado.getIdCliente());
        Assertions.assertEquals(clienteMock.getNome(), clienteEncontrado.getNome());
        Assertions.assertEquals(clienteMock.getTelefone(), clienteEncontrado.getTelefone());
        Assertions.assertEquals(clienteMock.getEmail(), clienteEncontrado.getEmail());

        Mockito.verify(clienteRepository, Mockito.times(1)).findById(id);
    }

    @Test
    public void buscarClientePorId_deveLancarExcecaoQuandoNaoExistir() {
        // given
        Long id = 1L;

        Mockito.when(clienteRepository.findById(id)).thenReturn(Optional.empty());

        // when
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> {
            clienteService.buscarClientePorId(id);
        });

        // then
        Assertions.assertEquals("Cliente informado por Id não encontrado", exception.getMessage());

        Mockito.verify(clienteRepository, Mockito.times(1)).findById(id);
    }

    @Test
    public void salvarCliente_deveSalvarClienteNoRepositorio() {
        // given
        ClientePostRequest clienteRequest = new ClientePostRequest("João da Silva", "123456789", "joao@example.com");
        ClienteModel clienteSalvo = new ClienteModel(1L, "João da Silva", "123456789", "joao@example.com");

        Mockito.when(clienteRepository.save(Mockito.any(ClienteModel.class))).thenReturn(clienteSalvo);

        // when
        ClienteModel clienteRetornado = clienteService.salvarCliente(clienteRequest);

        // then
        Assertions.assertEquals(clienteSalvo, clienteRetornado);
        Mockito.verify(clienteRepository, Mockito.times(1)).save(Mockito.any(ClienteModel.class));
    }

    @Test
    public void atualizarCliente_deveAtualizarClienteExistente() {
        // given
        Long id = 1L;
        ClientePutRequest clientePutRequest = new ClientePutRequest();
        clientePutRequest.setNome("Novo Nome");
        clientePutRequest.setEmail("novoemail@example.com");
        clientePutRequest.setTelefone("123456789");

        ClienteModel clienteAntigo = new ClienteModel();
        clienteAntigo.setIdCliente(id);
        clienteAntigo.setNome("Nome Antigo");
        clienteAntigo.setEmail("email@example.com");
        clienteAntigo.setTelefone("987654321");

        ClienteModel clienteAtualizado = new ClienteModel();
        clienteAtualizado.setIdCliente(id);
        clienteAtualizado.setNome(clientePutRequest.getNome());
        clienteAtualizado.setEmail(clientePutRequest.getEmail());
        clienteAtualizado.setTelefone(clientePutRequest.getTelefone());

        when(clienteRepository.findById(id)).thenReturn(Optional.of(clienteAntigo));
        when(clienteRepository.save(Mockito.any(ClienteModel.class))).thenReturn(clienteAtualizado);

        // when
        ClienteModel clienteRetornado = clienteService.atualizarCliente(id, clientePutRequest);

        // then
        Assertions.assertEquals(clienteAtualizado.getIdCliente(), clienteRetornado.getIdCliente());
        Assertions.assertEquals(clienteAtualizado.getNome(), clienteRetornado.getNome());
        Assertions.assertEquals(clienteAtualizado.getEmail(), clienteRetornado.getEmail());
        Assertions.assertEquals(clienteAtualizado.getTelefone(), clienteRetornado.getTelefone());

        Mockito.verify(clienteRepository, Mockito.times(1)).findById(id);
        Mockito.verify(clienteRepository, Mockito.times(1)).save(Mockito.any(ClienteModel.class));
    }

    @Test
    public void excluirCliente_deveExcluirClienteExistente() {
        // given
        Long id = 1L;

        // when
        clienteService.excluirCliente(id);

        // then
        Mockito.verify(clienteRepository, Mockito.times(1)).deleteById(id);
    }



}

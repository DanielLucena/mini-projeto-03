package com.jeanlima.springrestapiapp.rest.controllers;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.jeanlima.springrestapiapp.exception.ClienteNaoEncontradoException;
import com.jeanlima.springrestapiapp.model.Cliente;
import com.jeanlima.springrestapiapp.model.ItemPedido;
import com.jeanlima.springrestapiapp.model.Pedido;
import com.jeanlima.springrestapiapp.repository.ClienteRepository;
import com.jeanlima.springrestapiapp.rest.dto.AtualizacaoClienteDTO;
import com.jeanlima.springrestapiapp.rest.dto.InformacaoItemPedidoDTO;
import com.jeanlima.springrestapiapp.rest.dto.InformacoesClienteDTO;
import com.jeanlima.springrestapiapp.rest.dto.InformacoesPedidoDTO;
import com.jeanlima.springrestapiapp.service.ClienteService;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/api/clientes")
@RestController // anotação especializadas de controller - todos já anotados com response body!
public class ClienteController {

    // remover
    @Autowired
    private ClienteRepository clientes;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("{id}")
    public Cliente getClienteById(@PathVariable Integer id) {
        return clientes
                .findById(id)
                .orElseThrow(() -> // se nao achar lança o erro!
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente save(@RequestBody Cliente cliente) {
        return clientes.save(cliente);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        clientes.findById(id)
                .map(cliente -> {
                    clientes.delete(cliente);
                    return cliente;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));

    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id,
            @RequestBody Cliente cliente) {
        clientes
                .findById(id)
                .map(clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return clienteExistente;
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado"));
    }

    @GetMapping
    public List<Cliente> find(Cliente filtro) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        return clientes.findAll(example);
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateNome(@PathVariable Integer id,
            @RequestBody AtualizacaoClienteDTO dto) {
        if (dto.getNovoNome() != null) {
            clienteService.atualizaNome(id, dto.getNovoNome());
        }
        if (dto.getNovoCpf() != null) {
            clienteService.atualizaCpf(id, dto.getNovoCpf());
        }

    }

    @GetMapping("/detalhado/{id}")
    public InformacoesClienteDTO getClienteCompletoById(@PathVariable Integer id) {
        return clienteService
                .obterClienteCompleto(id)
                .map(p -> converter(p))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    private InformacoesClienteDTO converter(Cliente cliente) {
        return InformacoesClienteDTO
                .builder()
                .id(cliente.getId())
                .nome(cliente.getNome())
                .cpf(cliente.getCpf())
                .pedidos(converter(cliente.getPedidos()))
                .build();
    }

    private List<InformacoesPedidoDTO> converter(Set<Pedido> pedidos) {
        if (CollectionUtils.isEmpty(pedidos)) {
            return Collections.emptyList();
        }
        return pedidos.stream().map(
                pedido -> InformacoesPedidoDTO
                        .builder()
                        .codigo(pedido.getId())
                        .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                        .cpf(pedido.getCliente().getCpf())
                        .nomeCliente(pedido.getCliente().getNome())
                        .total(pedido.getTotal())
                        .status(pedido.getStatus().name())
                        .items(converter(pedido.getItens()))
                        .build())
                .collect(Collectors.toList());
    }

    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens) {
        if (CollectionUtils.isEmpty(itens)) {
            return Collections.emptyList();
        }
        return itens.stream().map(
                item -> InformacaoItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build())
                .collect(Collectors.toList());
    }
}

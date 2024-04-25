package com.jeanlima.springrestapiapp.service;

import java.util.Optional;

import com.jeanlima.springrestapiapp.model.Cliente;

public interface ClienteService {
    void atualizaNome(Integer id, String nome);

    void atualizaCpf(Integer id, String cpf);

    Optional<Cliente> obterClienteCompleto(Integer id);
}

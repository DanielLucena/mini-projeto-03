package com.jeanlima.springrestapiapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanlima.springrestapiapp.exception.ClienteNaoEncontradoException;
import com.jeanlima.springrestapiapp.repository.ClienteRepository;
import com.jeanlima.springrestapiapp.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void atualizaNome(Integer id, String nome) {
        clienteRepository
                .findById(id)
                .map(cliente -> {
                    cliente.setNome(nome);
                    return clienteRepository.save(cliente);
                }).orElseThrow(() -> new ClienteNaoEncontradoException());

    }

    @Override
    public void atualizaCpf(Integer id, String cpf) {
        clienteRepository
                .findById(id)
                .map(cliente -> {
                    cliente.setCpf(cpf);
                    return clienteRepository.save(cliente);
                }).orElseThrow(() -> new ClienteNaoEncontradoException());

    }
}

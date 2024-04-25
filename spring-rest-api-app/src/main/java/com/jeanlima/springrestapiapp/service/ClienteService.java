package com.jeanlima.springrestapiapp.service;

public interface ClienteService {
    void atualizaNome(Integer id, String nome);

    void atualizaCpf(Integer id, String cpf);
}

package com.jeanlima.springrestapiapp.service;

import com.jeanlima.springrestapiapp.model.Estoque;
import com.jeanlima.springrestapiapp.rest.dto.EstoqueDTO;

public interface EstoqueService {
    Estoque salvar(EstoqueDTO dto);

    Estoque getById(Integer id);

    Estoque atualizar(EstoqueDTO dto);

    void deletar(Integer id);

}

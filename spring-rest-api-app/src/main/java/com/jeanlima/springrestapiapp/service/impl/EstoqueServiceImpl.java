package com.jeanlima.springrestapiapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanlima.springrestapiapp.exception.ProdutoNaoEncontradoException;
import com.jeanlima.springrestapiapp.model.Estoque;
import com.jeanlima.springrestapiapp.model.Produto;
import com.jeanlima.springrestapiapp.repository.EstoqueRepository;
import com.jeanlima.springrestapiapp.repository.ProdutoRepository;
import com.jeanlima.springrestapiapp.rest.dto.EstoqueDTO;
import com.jeanlima.springrestapiapp.service.EstoqueService;

import jakarta.transaction.Transactional;

@Service
public class EstoqueServiceImpl implements EstoqueService {
    @Autowired
    EstoqueRepository repository;

    @Autowired
    ProdutoRepository produtoRepository;

    @Override
    @Transactional
    public Estoque salvar(EstoqueDTO dto) {
        Produto produto = produtoRepository
                .findById(dto.getProduto())
                .orElseThrow(() -> new ProdutoNaoEncontradoException());
        Estoque estoque = new Estoque();
        estoque.setProduto(produto);
        estoque.setQuantidade(dto.getQuantidade());
        repository.save(estoque);

        return estoque;
    }

    @Override
    public Estoque getById(Integer id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Estoque atualizar(EstoqueDTO dto) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deletar(Integer id) {
        // TODO Auto-generated method stub

    }
}

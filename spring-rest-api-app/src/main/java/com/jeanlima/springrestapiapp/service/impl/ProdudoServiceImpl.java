package com.jeanlima.springrestapiapp.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeanlima.springrestapiapp.exception.ProdutoNaoEncontradoException;
import com.jeanlima.springrestapiapp.repository.ProdutoRepository;
import com.jeanlima.springrestapiapp.service.ProdutoService;

@Service
public class ProdudoServiceImpl implements ProdutoService {
    @Autowired
    ProdutoRepository repository;

    @Override
    public void atualizaDescricao(Integer id, String descricao) {
        repository
                .findById(id)
                .map(produto -> {
                    produto.setDescricao(descricao);
                    return repository.save(produto);
                }).orElseThrow(() -> new ProdutoNaoEncontradoException());
    }

    @Override
    public void atualizaPreco(Integer id, BigDecimal preco) {
        repository
                .findById(id)
                .map(produto -> {
                    produto.setPreco(preco);
                    return repository.save(produto);
                }).orElseThrow(() -> new ProdutoNaoEncontradoException());
    }
}

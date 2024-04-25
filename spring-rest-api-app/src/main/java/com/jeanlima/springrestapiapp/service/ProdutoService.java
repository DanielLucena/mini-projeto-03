package com.jeanlima.springrestapiapp.service;

import java.math.BigDecimal;

public interface ProdutoService {
    public void atualizaDescricao(Integer id, String descricao);

    public void atualizaPreco(Integer id, BigDecimal preco);

}

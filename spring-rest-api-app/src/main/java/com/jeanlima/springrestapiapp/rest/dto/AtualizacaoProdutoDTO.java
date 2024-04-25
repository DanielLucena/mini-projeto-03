package com.jeanlima.springrestapiapp.rest.dto;

import java.math.BigDecimal;

public record AtualizacaoProdutoDTO(String novaDescricao, BigDecimal novoPreco) {

}

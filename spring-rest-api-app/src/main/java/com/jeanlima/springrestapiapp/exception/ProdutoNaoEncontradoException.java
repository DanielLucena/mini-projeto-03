package com.jeanlima.springrestapiapp.exception;

public class ProdutoNaoEncontradoException extends RuntimeException {
    public ProdutoNaoEncontradoException() {
        super("Pedido não encontrado.");
    }
}

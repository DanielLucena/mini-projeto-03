package com.jeanlima.springrestapiapp.exception;

public class ClienteNaoEncontradoException extends RuntimeException {
    public ClienteNaoEncontradoException() {
        super("Cliente não encontrado.");
    }
}

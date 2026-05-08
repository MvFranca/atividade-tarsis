package com.atividade.saque.exception;

public class ContaNaoEncontradaException extends RuntimeException {

    public ContaNaoEncontradaException(Long contaId) {
        super("Conta com id " + contaId + " nao foi encontrada.");
    }
}

package com.atividade.saque.exception;

import java.math.BigDecimal;

public class SaldoInsuficienteException extends RuntimeException {

    public SaldoInsuficienteException(BigDecimal saldoAtual, BigDecimal valorSaque) {
        super("Saldo insuficiente. Saldo atual: " + saldoAtual + ", valor solicitado: " + valorSaque + ".");
    }
}

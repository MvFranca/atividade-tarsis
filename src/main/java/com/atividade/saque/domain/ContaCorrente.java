package com.atividade.saque.domain;

import java.math.BigDecimal;

public class ContaCorrente {

    private final Long id;
    private final String titular;
    private final String email;
    private BigDecimal saldo;

    public ContaCorrente(Long id, String titular, String email, BigDecimal saldo) {
        this.id = id;
        this.titular = titular;
        this.email = email;
        this.saldo = saldo;
    }

    public Long getId() {
        return id;
    }

    public String getTitular() {
        return titular;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void sacar(BigDecimal valor) {
        this.saldo = this.saldo.subtract(valor);
    }
}

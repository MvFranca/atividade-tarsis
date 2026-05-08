package com.atividade.saque.exception;

import java.math.BigDecimal;

public class ValorSaqueInvalidoException extends RuntimeException {

    public ValorSaqueInvalidoException(BigDecimal valor) {
        super("Valor de saque invalido: " + valor + ". O valor deve ser maior que zero.");
    }
}

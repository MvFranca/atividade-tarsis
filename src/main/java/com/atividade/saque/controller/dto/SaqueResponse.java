package com.atividade.saque.controller.dto;

import java.math.BigDecimal;

public record SaqueResponse(
        String mensagem,
        Long contaId,
        String titular,
        BigDecimal valorSacado,
        BigDecimal saldoAtualizado
) {
}

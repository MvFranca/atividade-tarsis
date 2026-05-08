package com.atividade.saque.messaging;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

public record SaqueRealizadoEvent(
        Long contaId,
        String titular,
        String email,
        BigDecimal valorSaque,
        BigDecimal saldoAtualizado,
        OffsetDateTime dataHora
) {
}

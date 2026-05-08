package com.atividade.saque.controller.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SaqueRequest(
        @NotNull(message = "O valor do saque e obrigatorio.")
        @DecimalMin(value = "0.01", message = "O valor do saque deve ser maior que zero.")
        BigDecimal valor
) {
}

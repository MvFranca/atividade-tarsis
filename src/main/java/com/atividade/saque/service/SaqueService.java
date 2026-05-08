package com.atividade.saque.service;

import com.atividade.saque.domain.ContaCorrente;
import com.atividade.saque.exception.SaldoInsuficienteException;
import com.atividade.saque.exception.ValorSaqueInvalidoException;
import com.atividade.saque.messaging.SaqueEventPublisher;
import com.atividade.saque.messaging.SaqueRealizadoEvent;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class SaqueService {

    private final ContaService contaService;
    private final SaqueEventPublisher saqueEventPublisher;

    public SaqueService(ContaService contaService, SaqueEventPublisher saqueEventPublisher) {
        this.contaService = contaService;
        this.saqueEventPublisher = saqueEventPublisher;
    }

    public ContaCorrente realizarSaque(Long contaId, BigDecimal valor) {
        ContaCorrente conta = contaService.buscarPorId(contaId);

        if (valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new ValorSaqueInvalidoException(valor);
        }

        if (conta.getSaldo().compareTo(valor) < 0) {
            throw new SaldoInsuficienteException(conta.getSaldo(), valor);
        }

        conta.sacar(valor);

        SaqueRealizadoEvent event = new SaqueRealizadoEvent(
                conta.getId(),
                conta.getTitular(),
                conta.getEmail(),
                valor,
                conta.getSaldo(),
                OffsetDateTime.now()
        );
        saqueEventPublisher.publicar(event);

        return conta;
    }
}

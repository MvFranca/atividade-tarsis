package com.atividade.saque.service;

import com.atividade.saque.domain.ContaCorrente;
import com.atividade.saque.exception.ContaNaoEncontradaException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ContaService {

    private final Map<Long, ContaCorrente> contas = new ConcurrentHashMap<>();

    public ContaService() {
        contas.put(1L, new ContaCorrente(1L, "Maria Silva", "maria.silva@email.com", new BigDecimal("1000.00")));
        contas.put(2L, new ContaCorrente(2L, "Joao Souza", "joao.souza@email.com", new BigDecimal("500.00")));
    }

    public ContaCorrente buscarPorId(Long contaId) {
        ContaCorrente conta = contas.get(contaId);
        if (conta == null) {
            throw new ContaNaoEncontradaException(contaId);
        }
        return conta;
    }
}

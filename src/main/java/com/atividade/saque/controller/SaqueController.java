package com.atividade.saque.controller;

import com.atividade.saque.controller.dto.SaqueRequest;
import com.atividade.saque.controller.dto.SaqueResponse;
import com.atividade.saque.domain.ContaCorrente;
import com.atividade.saque.service.SaqueService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contas")
public class SaqueController {

    private final SaqueService saqueService;

    public SaqueController(SaqueService saqueService) {
        this.saqueService = saqueService;
    }

    @PostMapping("/{id}/saque")
    public ResponseEntity<SaqueResponse> realizarSaque(
            @PathVariable("id") Long contaId,
            @Valid @RequestBody SaqueRequest request
    ) {
        ContaCorrente contaAtualizada = saqueService.realizarSaque(contaId, request.valor());
        SaqueResponse response = new SaqueResponse(
                "Saque realizado com sucesso.",
                contaAtualizada.getId(),
                contaAtualizada.getTitular(),
                request.valor(),
                contaAtualizada.getSaldo()
        );
        return ResponseEntity.ok(response);
    }
}

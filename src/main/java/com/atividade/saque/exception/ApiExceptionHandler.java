package com.atividade.saque.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ContaNaoEncontradaException.class)
    public ResponseEntity<ErroResponse> handleContaNaoEncontrada(ContaNaoEncontradaException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro(HttpStatus.NOT_FOUND, ex.getMessage()));
    }

    @ExceptionHandler({SaldoInsuficienteException.class, ValorSaqueInvalidoException.class})
    public ResponseEntity<ErroResponse> handleRegraNegocio(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidacao(MethodArgumentNotValidException ex) {
        Map<String, String> detalhes = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> detalhes.put(fieldError.getField(), fieldError.getDefaultMessage()));

        ErroResponse response = new ErroResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Falha de validacao nos dados da requisicao.",
                OffsetDateTime.now(),
                detalhes
        );
        return ResponseEntity.badRequest().body(response);
    }

    private ErroResponse erro(HttpStatus status, String mensagem) {
        return new ErroResponse(status.value(), mensagem, OffsetDateTime.now(), Map.of());
    }

    public record ErroResponse(
            int status,
            String mensagem,
            OffsetDateTime timestamp,
            Map<String, String> detalhes
    ) {
    }
}

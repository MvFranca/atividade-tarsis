package com.atividade.saque.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SaqueEventSubscriber {

    @RabbitListener(queues = "${app.rabbit.queue}")
    public void consumirEventoSaque(SaqueRealizadoEvent event) {
        String mensagem = """
                [SIMULACAO EMAIL]
                Para: %s
                Ola, %s!
                Seu saque foi realizado com sucesso.
                Valor sacado: R$ %s
                Saldo atualizado: R$ %s
                Data/Hora: %s
                -----------------------------------------
                """.formatted(
                event.email(),
                event.titular(),
                event.valorSaque(),
                event.saldoAtualizado(),
                event.dataHora()
        );

        System.out.println(mensagem);
    }
}

# Saque Assincrono com RabbitMQ

Aplicacao em Java com Spring Boot que realiza saque em conta corrente, valida saldo, atualiza o saldo disponivel e publica um evento no RabbitMQ. Em paralelo, um subscriber consome o evento e simula o envio de e-mail no console.

## Tecnologias

- Java 17
- Spring Boot
- RabbitMQ
- Docker Compose

## Como executar

1. Suba o RabbitMQ:

```bash
docker compose up -d
```

2. Entre na pasta que contem o `pom.xml` e inicie a aplicacao Spring.

Recomendado (nao precisa ter Maven instalado; baixa o Maven na primeira execucao):

```bash
chmod +x ./mvnw   # so na primeira vez, se precisar
./mvnw spring-boot:run
```

Alternativa, se voce tiver Maven instalado (por exemplo `brew install maven`):

```bash
mvn spring-boot:run
```

Se aparecer `zsh: command not found: mvn`, use sempre `./mvnw` a partir da pasta do projeto.

## Endpoint de saque

- Metodo: `POST`
- URL: `http://localhost:8080/contas/{id}/saque`
- Exemplo: `http://localhost:8080/contas/1/saque`

Body JSON:

```json
{
  "valor": 150.00
}
```

Resposta esperada (sucesso):

```json
{
  "mensagem": "Saque realizado com sucesso.",
  "contaId": 1,
  "titular": "Maria Silva",
  "valorSacado": 150.00,
  "saldoAtualizado": 850.00
}
```

## Como validar o fluxo assincrono

Ao chamar o endpoint com sucesso:

1. o saque e processado no servico;
2. o evento `saque.realizado` e publicado no RabbitMQ;
3. o subscriber recebe a mensagem da fila;
4. uma simulacao de envio de e-mail e exibida no console com os dados do saque.

Exemplo de saida no console:

```text
[SIMULACAO EMAIL]
Para: maria.silva@email.com
Ola, Maria Silva!
Seu saque foi realizado com sucesso.
Valor sacado: R$ 150.00
Saldo atualizado: R$ 850.00
Data/Hora: 2026-05-08T19:30:00-03:00
-----------------------------------------
```

## RabbitMQ Management

- URL: `http://localhost:15672`
- Usuario: `guest`
- Senha: `guest`

## Contas em memoria para teste

- Conta `1`: Maria Silva, saldo inicial `1000.00`
- Conta `2`: Joao Souza, saldo inicial `500.00`

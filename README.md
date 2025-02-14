
# Desafio Backend Magalu ms

Este foi um desafio proposto pela Magalu, o intuito era desenvolver uma API Restfull que realizasse o gerenciamento de envios de notificações de um APP de comunicação. 

## Requisitos do desafio

Deveria ter um endpoint que receba uma solicitação de agendamento de envio de comunicação (1):

Este endpoint precisa ter no mínimo os seguintes campos:

    . Data/Hora para o envio
    . Destinatário
    . Mensagem a ser entregue

 As possíveis comunicações que podem ser enviadas são: 
    
    . email, SMS, push e WhatsApp.
    
Deve ter um endpoint para consultar o status do agendamento de envio de comunicação (2). O agendamento será feito no endpoint (1) e a consulta será feita por este outro endpoint.

## Tecnologias: 

    - Java 21
    - Spring Boot
    - Spring Scheduler
    - Docker
    - Postman
    - Beekeeper
    - MySQL



## Como usar:

Interação com o Banco de Dados MySql para inserção de uma nova notificação


    POST / localhost:8080/notifications

    {
        "dateTime": "2025-02-13T23:24:00", 
        "destination": "destinatario (teste@teste.teste...)",
        "message": "Mensagem",
        "channel": "canal de envio (EMAIL, WHATSAPP, PUSH, SMS"
    }

Get notificação

    GET / localhost:8080/notifications/id

Cancelar o envio de uma notificação

    PUT / localhost:8080/notifications/id

Limpar fila de notificações

    DELETE / localhost:8080/notifications/all





## Confira o desafio completo

 - [clicando aqui](./problem.md)

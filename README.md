
# Spring Boot Vue Transactions

Aplicações backend Spring Boot com java 17 e front end com Vuejs



## Aplicações

**Client:** Vuejs 3, bootstrap, vue-router,axios, sweetalert2, v-money3, maska

**Server:** Spring-boot 3 (WEB, Data-JPA, Test), H2,lombok, Swagger, Model mapper


## Authors

- [Fabio Gomes de Abreu](https://github.com/Fagoabreu)


## Backend Config
Config file location: ./backend/transferencias/src/main/resources/application.properties

DB_URL: H2 database file url, default value: jdbc:h2:file:~/h2/transferenciadb

DB_CONSOLE_URL: H2 console url, default Value: jdbc:h2:file:~/h2/transferenciadb

DB_USER: Database user name, default: sa

DB_PASS: Database password, default: NONE

FRONTEND_URL: fontend url for Cors config, default http://localhost:8081

Inital database data file: ./backend/transferencias/src/main/resources/data.sql: 

## Front Config
Config file location: /frontend/transferencias/.env
VUE_APP_API_BASE_URL: backend link url, default http://localhost:8080/api


## Links

 - Backend URL - http://localhost:8080
 - H2 database console - http://localhost:8080/h2-console
 - Swagguer documentação de API - http://localhost:8080/swagger-ui/index.html
 - Frontend URL - http://localhost:8081
## Rules

Avaliação

Desenvolver tanto a API quanto o front-end (Spring boot e VueJs no front, caso não tenha
conhecimentos de VueJs, pode ser feito o front com angular).

O objetivo dessa tarefa é avaliar como você vai desenvolver o código em termos de estilo, eficiência,qualidade e prazo de entrega.

A tarefa é a seguinte:

Desenvolver um sistema de agendamento de transferências financeiras.
O usuário deve poder agendar uma transferência financeira com as seguintes informações:

1. Conta de origem (padrão XXXXXXXXXX), conta de destino (padrão XXXXXXXXXX), valor da
transferência, taxa (a ser calculada conforme tabela abaixo), data da transferência (data em
que será realizada a transferência) e data de agendamento (hoje);

2. O cálculo da taxa sobre o valor a ser transferido depende da data de transferência como
segue:

| Dias Transferência | R$    | Taxa De Até  |
| :-------- | :------- | :------------------------- |
| `0 - 0` | `3,00` | 2,5% |
| `1 - 10` | `12,00` | 0,0% |
| `11 - 20` | `0,00` | 8,2% |
| `21 - 30` | `0,00` | 6,9% |
| `31 - 40` | `0,00` | 4,7% |
| `41 - 50` | `0,00` | 1,7% |

Observação: Caso não haja taxa aplicável, lançar um alerta sobre o erro e não permitir
tranferência.
3. O usuário deve poder ver o extrato de todos os agendamentos cadastrados.
Nota: A persistência deve ser feita em banco de dados em memória (h2, por exemplo). 


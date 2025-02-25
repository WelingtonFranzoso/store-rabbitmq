# Spring Boot com RabbitMQ

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/WelingtonFranzoso/store-rabbitmq/blob/main/LICENSE) 

# Sobre o projeto
Este projeto consiste em três microserviços desenvolvidos com Spring Boot, utilizando RabbitMQ para comunicação assíncrona entre eles.


## A API producer-store-api é responsável por várias funcionalidades, como:
- Criação dinâmica de filas no RabbitMQ, conforme a necessidade de diferentes tipos de mensagens.
- Estabelecimento e gerenciamento de conexões com exchanges RabbitMQ para o roteamento eficiente das mensagens.
- Envio de requisições de atualização dos atributos das classes PriceDTO e StockDTO, que são compartilhadas entre os microserviços.
- Publicação de mensagens nas filas específicas do RabbitMQ para as alterações de preço e estoque.


## A API consumer-price-api possui as seguintes funcionalidades:
- Consome mensagens da fila de preço do RabbitMQ.
- Realiza o processamento das alterações de preço, aplicando regras de negócio específicas para atualizar o preço do produto.
- Implementa tratamento de exceções personalizadas, garantindo o manejo adequado de erros durante o consumo das mensagens.


## A API consumer-stock-api oferece funcionalidades similares, mas voltadas para o controle de estoque:
- Consome mensagens da fila de estoque do RabbitMQ.
- Processa as alterações de estoque de acordo com as regras definidas.
- Inclui exceções personalizadas para garantir o tratamento adequado de falhas durante o consumo das mensagens.


Ambas as APIs consumidoras (price e stock) garantem maior resiliência e confiabilidade no processo de comunicação assíncrona com o RabbitMQ, utilizando um sistema de tratamento de erros robusto para minimizar falhas e perdas de mensagens.


# Tecnologias utilizadas

- Java 17+
- Spring Boot
- Maven
- RabbitMQ
- Docker

# Como executar o projeto
## Back end
### Pré-requisitos: 
- Java 17 ou superior
- Maven (para construção do projeto)

```bash
# clonar repositório
git clone git@github.com:WelingtonFranzoso/sb-rabbitmq.git

# entrar na pasta do projeto back end
cd store-rabbitmq

# Subindo um ambiente Kafka via Docker
docker-compose up -d

# entrar na pasta do projeto Producer
cd producer-store-api

# executar o projeto
./mvnw spring-boot:run

# entrar na pasta do projeto Consumer-price
cd consumer-price-api

# executar o projeto
./mvnw spring-boot:run

# entrar na pasta do projeto Consumer-stock
cd consumer-stock-api

# executar o projeto
./mvnw spring-boot:run
```

# Endpoints Disponíveis

| Método | Endpoint      | Descrição             |
|:------:|:-------------:|:---------------------:|
| Post    | /price      | Envia uma mensagem de alteração do preço para uma fila do RabbitMQ |
| Post    | /stock      | Envia uma mensagem de alteração do estoque para uma fila do RabbitMQ |



# Contribuição

1. Fork este repositório

2. Crie uma branch (feature-nova)

3. Commit suas mudanças (git commit -m 'Add nova feature')

4. Push para sua branch (git push origin feature-nova)

5. Crie um Pull Request

# Licença

- Este projeto está sob a licença MIT. Sinta-se livre para usá-lo e modificá-lo.

﻿# CityVibe

## 💻 Sobre o Projeto CityVibe

[Veja como ficou em Produção!](https://city-vibe-cjc3gae3fphaa9gu.canadacentral-01.azurewebsites.net)

[Swagger](https://city-vibe-cjc3gae3fphaa9gu.canadacentral-01.azurewebsites.net/swagger-ui/index.html)

## ⚙️ Funcionalidades

A API oferece as seguintes funcionalidades:
- **CRUD de Eventos**: Criação, leitura de eventos;
- **Upload de Imagens**: Suporte para upload e visualização de imagens (capas) dos eventos.

## 🛠 Tecnologias Utilizadas

A API REST do projeto foi desenvolvida utilizando as seguintes tecnologias:
- **Java 17**: Linguagem de programação utilizada.
- **Spring Boot 3**: Framework para desenvolvimento de aplicações Java.
- **Maven**: Ferramenta de automação e gerenciamento de dependências.
- **Hibernate**: Framework de mapeamento objeto-relacional (ORM).
- **Swagger**: Ferramenta para definir, criar, documentar e consumir APIs REST.
- **Azure**: Plataforma de nuvem.
- **JUnit 5**: Framework para testes automatizados.
- **Mockito**: Framework para simulação de objetos (mocking) em testes.

## 📝 Licença

Este projeto foi desenvolvido sob licença MIT.

## 📋 Estrutura do Projeto

O projeto é dividido em várias camadas:
- **Controller**: Gerencia as requisições HTTP.
- **Service**: Lógica de negócios.
- **Repository**: Interação com o banco de dados.
- **DTO (Data Transfer Object)**: Objetos que facilitam a troca de dados entre as camadas.

## 🛠 Passos para Configuração

1. Clone o repositório:

   ```bash
   git clone https://github.com/usuário/cityVibe

2. Navegue até o diretório do projeto:

   ```bash
   cd <diretório-do-projeto>


3. Navegue até o diretório do projeto:

    ```bash
    mvn clean install


4. Execute os Testes Unitários:

    ```bash
    mvn test


5. Após iniciar a aplicação, acesse o app:

    ```bash
    start https://city-vibe-cjc3gae3fphaa9gu.canadacentral-01.azurewebsites.net


6. Após iniciar a aplicação e fazer login, acesse o Swagger para visualizar todos endpoints da API:

    ```bash
    start https://city-vibe-cjc3gae3fphaa9gu.canadacentral-01.azurewebsites.net/swagger-ui/index.html


![Print of System](/img.PNG)

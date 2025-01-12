# CityVibe

## 💻 Sobre o Projeto CityVibe

[Veja como ficou em Produção!]()

[Swagger]()

## ⚙️ Funcionalidades

A API oferece as seguintes funcionalidades:
- **CRUD de Eventos**: Criação, leitura, atualização e exclusão de registros de eventos (Shows, Stand-Up, Gospel, etc.).
- **Filtro por Categoria**: Possibilidade de buscar eventos por categoria.
- **Filtro por Data e Hora**: Permite buscar eventos de acordo com a data e horário.
- **Upload de Imagens**: Suporte para upload e visualização de imagens (capas) dos eventos.

## 🛠 Tecnologias Utilizadas

A API REST do projeto foi desenvolvida utilizando as seguintes tecnologias:
- **Java 17**: Linguagem de programação utilizada.
- **Spring Boot 3**: Framework para desenvolvimento de aplicações Java.
- **Maven**: Ferramenta de automação e gerenciamento de dependências.
- **MySQL**: Banco de dados relacional.
- **Hibernate**: Framework de mapeamento objeto-relacional (ORM).
- **Flyway**: Ferramenta para versionamento e migração de banco de dados.
- **Lombok**: Biblioteca para reduzir o boilerplate no código Java.
- **Swagger**: Ferramenta para definir, criar, documentar e consumir APIs REST.
- **Docker**: Plataforma que facilita a criação, distribuição e execução de aplicativos em contêineres.
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
    start http://localhost:8080


6. Após iniciar a aplicação e fazer login, acesse o Swagger para visualizar todos endpoints da API:

    ```bash
    start http://localhost:8080/swagger-ui/index.html


![Diagram of System Architecture](/img.PNG)
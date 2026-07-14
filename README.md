# 📚 Library API

Uma API RESTful completa para gerenciamento de biblioteca desenvolvida com Spring Boot, implementando autenticação OAuth2, validações robustas e documentação automática com Swagger.

[![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.4-brightgreen?style=flat-square&logo=spring-boot)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.8.1-blue?style=flat-square&logo=apachemaven)](https://maven.apache.org/)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Latest-336791?style=flat-square&logo=postgresql)](https://www.postgresql.org/)
[![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)](LICENSE)

## 🎯 Sobre o Projeto

A **Library API** é uma aplicação completa para gerenciamento de biblioteca que permite:

- ✅ Cadastro e gerenciamento de autores
- ✅ Cadastro e gerenciamento de livros
- ✅ Gerenciamento de usuários e autenticação
- ✅ Autorização com OAuth2
- ✅ Validação robusta de dados
- ✅ Documentação interativa (Swagger/OpenAPI)
- ✅ Tratamento centralizado de exceções
- ✅ Logging e monitoring com Spring Actuator

## 🛠️ Tecnologias Utilizadas

### Core
- **Java 21** - Linguagem de programação
- **Spring Boot 3.3.4** - Framework web e microserviços
- **Maven 3.8.1** - Gerenciador de dependências

### Backend
- **Spring Web** - Desenvolvimento REST APIs
- **Spring Data JPA** - Persistência de dados
- **Spring Security** - Autenticação e autorização
- **Spring OAuth2** - OAuth2 Client e Authorization Server
- **Spring Validation** - Validação de dados
- **Spring Actuator** - Monitoring e health checks

### Banco de Dados
- **PostgreSQL** - Banco de dados relacional

### Mapeamento & Transformação
- **MapStruct** - Mapeamento entre entidades e DTOs
- **Lombok** - Redução de boilerplate (getters, setters, constructors)
- **Hypersistence Utils** - Utilitários avançados para Hibernate

### Documentação
- **SpringDoc OpenAPI** - Documentação automática com Swagger UI

### Views
- **Thymeleaf** - Template engine para server-side rendering

### Testes
- **Spring Boot Test** - Testes integrados e unitários

## 📋 Pré-requisitos

Antes de começar, verifique se possui os seguintes itens instalados:

- **Java 21** ou superior
  ```bash
  java -version
  ```

- **Maven 3.8.1** ou superior
  ```bash
  mvn -version
  ```

- **PostgreSQL 12** ou superior
  ```bash
  psql --version
  ```

- **Git**
  ```bash
  git --version
  ```

## 🚀 Instalação

### 1. Clone o repositório

```bash
git clone https://github.com/IuryRayam/library-api.git
cd libraryapi
```

### 2. Configure as variáveis de ambiente

Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis:

```env
# Banco de Dados
SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/libraryapi
SPRING_DATASOURCE_USERNAME=seu_usuario
SPRING_DATASOURCE_PASSWORD=sua_senha
SPRING_JPA_HIBERNATE_DDL_AUTO=update

# Logging
LOGGING_LEVEL_ROOT=INFO
LOGGING_LEVEL_COM_GITHUB_IURYRAYAM=DEBUG

# OAuth2
SPRING_SECURITY_OAUTH2_RESOURCESERVER_JWT_ISSUER_URI=http://localhost:8080
```

### 3. Configure o banco de dados PostgreSQL

```bash
# Acesse o PostgreSQL
psql -U postgres

# Crie o banco de dados
CREATE DATABASE libraryapi;
CREATE USER libraryapi_user WITH PASSWORD 'sua_senha';
ALTER ROLE libraryapi_user SET client_encoding TO 'utf8';
ALTER ROLE libraryapi_user SET default_transaction_isolation TO 'read committed';
ALTER ROLE libraryapi_user SET default_transaction_deferrable TO on;
GRANT ALL PRIVILEGES ON DATABASE libraryapi TO libraryapi_user;
\q
```

### 4. Construa o projeto

```bash
# Com Maven
mvn clean install
```

### 5. Execute a aplicação

```bash
# Com Maven
mvn spring-boot:run
```

Ou execute o arquivo JAR gerado:

```bash
java -jar target/libraryapi-0.0.1-SNAPSHOT.jar
```

A aplicação estará disponível em: `http://localhost:8080`

## 📖 Uso

### Acessar a Documentação Swagger

Após iniciar a aplicação, acesse a documentação interativa:

```
http://localhost:8080/swagger-ui.html
```

### Endpoints Principais

#### 📕 Autores
- `GET /api/autores` - Listar todos os autores
- `GET /api/autores/{id}` - Obter um autor específico
- `POST /api/autores` - Criar novo autor
- `PUT /api/autores/{id}` - Atualizar autor
- `DELETE /api/autores/{id}` - Deletar autor

#### 📗 Livros
- `GET /api/livros` - Listar todos os livros
- `GET /api/livros/{id}` - Obter um livro específico
- `POST /api/livros` - Criar novo livro
- `PUT /api/livros/{id}` - Atualizar livro
- `DELETE /api/livros/{id}` - Deletar livro
- `GET /api/livros/pesquisa` - Pesquisar livros com filtros

#### 👤 Usuários
- `GET /api/usuarios` - Listar usuários
- `POST /api/usuarios` - Registrar novo usuário
- `PUT /api/usuarios/{id}` - Atualizar usuário

#### 🔐 Autenticação
- `POST /login` - Login e obter JWT
- `GET /logout` - Logout

#### 🏥 Health Check
- `GET /actuator/health` - Status da aplicação

### Exemplo de Requisição

```bash
# Cadastrar um novo livro
curl -X POST http://localhost:8080/api/livros \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer SEU_TOKEN_JWT" \
  -d '{
    "titulo": "Clean Code",
    "isbn": "9780132350884",
    "dataPublicacao": "2008-08-01",
    "genero": "TECNOLOGIA",
    "autorId": 1
  }'
```

## 🏗️ Estrutura do Projeto

```
libraryapi/
├── src/
│   ├── main/
│   │   ├── java/com/github/iuryrayam/libraryapi/
│   │   │   ├── config/              # Configurações (Security, Database, OpenAPI)
│   │   │   ├── controller/          # REST Controllers
│   │   │   │   ├── dto/            # Data Transfer Objects
│   │   │   │   ├── mappers/        # MapStruct Mappers
│   │   │   │   └── common/         # Exception Handler Global
│   │   │   ├── model/              # Entidades JPA
│   │   │   ├── service/            # Lógica de negócio
│   │   │   ├── repository/         # Data Access Layer
│   │   │   ├── security/           # Configurações de segurança
│   │   │   ├── validator/          # Validadores customizados
│   │   │   ├── exception/          # Exceções customizadas
│   │   │   └── Application.java    # Classe principal
│   │   └── resources/
│   │       ├── application.yml
│   │       └── templates/          # Thymeleaf templates
│   └── test/
│       └── java/                   # Testes unitários e integrados
├── pom.xml                          # Configuração Maven
├── Dockerfile                       # Configuração Docker
└── README.md                        # Este arquivo
```

## 🔐 Segurança

A aplicação implementa:

- **Spring Security** com OAuth2
- **JWT (JSON Web Tokens)** para autenticação stateless
- **Validação de entrada** em todos os endpoints
- **CORS** configurado para controlar acesso cruzado
- **HTTPS** recomendado em produção

### Fluxo de Autenticação

1. Usuário faz login com credenciais
2. Servidor valida e emite um JWT
3. Cliente inclui o JWT em cada requisição
4. Servidor valida o token antes de processar

## 🧪 Testes

Execute os testes com Maven:

```bash
# Executar todos os testes
mvn test

# Executar com cobertura
mvn test jacoco:report

# Executar testes específicos
mvn test -Dtest=AutorControllerTest
```

## 📦 Build para Produção

### Gerar JAR Executável

```bash
mvn clean package
```

O JAR será gerado em `target/libraryapi-0.0.1-SNAPSHOT.jar`

### Build com Docker

```bash
# Build da imagem
docker build -t libraryapi:latest .

# Executar container
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://db:5432/libraryapi \
  -e SPRING_DATASOURCE_USERNAME=libraryapi_user \
  -e SPRING_DATASOURCE_PASSWORD=sua_senha \
  libraryapi:latest
```

## 📊 Monitoring

A aplicação disponibiliza endpoints de monitoramento através do Spring Actuator:

```bash
# Health check
curl http://localhost:8080/actuator/health

# Métricas
curl http://localhost:8080/actuator/metrics

# Informações da aplicação
curl http://localhost:8080/actuator/info
```

## 🤝 Contribuindo

Contribuições são bem-vindas! Por favor:

1. Faça um Fork do repositório
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

## 📝 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

## ✉️ Contato

**Iury Rayam**
- GitHub: [@IuryRayam](https://github.com/IuryRayam)
- Email: [iury2155@gmail.com]

---

<div align="center">

Desenvolvido usando Spring Boot

⭐ Se esse projeto foi útil para você, considere dar uma estrela!

</div>

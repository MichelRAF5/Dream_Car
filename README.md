# Dream Car

Cadastro, login e perfil de usuários com senhas armazenadas em hash (bcrypt) em um banco H2 em memória.
API em Java + Spring Boot e telas em HTML, CSS e JavaScript.

---

## Pré-requisitos

- JDK 21 ou superior
- Maven

> Não é preciso instalar nenhum banco de dados: o projeto usa o **H2 em memória**, que sobe junto com a aplicação.

---

## Como executar

1. **Conferir a conexão** em `src/main/resources/application.properties`:

```properties
   spring.datasource.url=jdbc:h2:mem:dreamcardb
   spring.datasource.driver-class-name=org.h2.Driver
   spring.datasource.username=sa
   spring.datasource.password=

   spring.h2.console.enabled=true
   spring.h2.console.path=/h2-console

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
```

As tabelas (`users` e `enderecos`) são criadas automaticamente pelo Hibernate ao iniciar, então não há script SQL para executar.

2. **Iniciar a API** pela IDE (classe com `@SpringBootApplication`).
   > A API sobe em `http://localhost:8080`.

3. **Abrir as telas** (`login.html`, `cadastro.html`, `home.html` e `perfil.html`) no navegador (Live Server ou servidor do IntelliJ).
   As origens liberadas para chamar a API são `localhost:5500`, `127.0.0.1:5500` e `localhost:63342`.

---

## Conexão com o banco H2

O banco roda **em memória**, dentro da própria aplicação. Por isso os dados são apagados sempre que a API é reiniciada, e será preciso cadastrar o usuário de novo.

### Acessar o console web do H2

1. Com a API rodando, abra `http://localhost:8080/h2-console`.
2. Preencha os campos de login:

   | Campo | Valor |
      |-------|-------|
   | Saved Settings | Generic H2 (Embedded) |
   | Driver Class | `org.h2.Driver` |
   | JDBC URL | `jdbc:h2:mem:dreamcardb` |
   | User Name | `sa` |
   | Password | *(vazio)* |

3. Clique em **Connect**.
4. Para ver os dados cadastrados, execute:

```sql
   SELECT * FROM users;
   SELECT * FROM enderecos;
```

> A senha aparece como hash bcrypt (começa com `$2a$`), nunca em texto puro.

### Problemas comuns

- **"Database not found" no console:** a URL precisa ser exatamente `jdbc:h2:mem:dreamcardb`, e a API precisa estar em execução. Como o banco é em memória, ele só existe enquanto a aplicação está rodando.
- **Tabelas vazias após reiniciar:** comportamento esperado do banco em memória.

### Voltar para o MySQL (opcional)

No `pom.xml`, descomente a dependência `mysql-connector-j` e, no `application.properties`, comente as linhas do H2 e descomente as do MySQL.

---

## Endpoints

| Método | Rota | Descrição |
|--------|------|-----------|
| `POST` | `/users/cadastrar` | Cadastra usuário e endereço |
| `POST` | `/users/login` | Valida email e senha |
| `GET`  | `/users/perfil?email=...` | Retorna os dados cadastrais do usuário (sem a senha) |

### `POST /users/cadastrar`

```json
{
  "email": "usuario@email.com",
  "senha": "minhasenha",
  "nome": "Nome",
  "sobrenome": "Sobrenome",
  "telefone": "31999999999",
  "cep": "30000000",
  "rua": "Rua Exemplo",
  "numero": "100",
  "bairro": "Centro",
  "cidade": "Belo Horizonte",
  "estado": "MG",
  "observacao": "Apto 101"
}
```

Respostas: `201` cadastrado, `400` email/senha ausentes, `409` email já registrado.

### `POST /users/login`

```json
{ "email": "usuario@email.com", "senha": "minhasenha" }
```

Respostas: `200` sucesso, `400` campos vazios, `401` email ou senha errados.

### `GET /users/perfil?email=usuario@email.com`

Respostas: `200` com os dados do usuário e endereço, `404` usuário não encontrado.

---
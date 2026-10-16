# Dream Car

Cadastro e login de usuários com senhas armazenadas em hash (bcrypt) no MySQL.
API em Java + Spring Boot e telas em HTML, CSS e JavaScript.

----

## Pré-requisitos

- JDK 25
- Maven
- MySQL em execução

---

## Como executar

1. **Criar o banco e a tabela:** execute o script `database/dreamcar.sql` no MySQL.

2. **Configurar a conexão** em `src/main/resources/application.properties`:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/fastSite
   spring.datasource.username=SEU_USUARIO
   spring.datasource.password=SUA_SENHA

   spring.jpa.hibernate.ddl-auto=validate
   ```

3. **Iniciar a API** pela IDE (classe com `@SpringBootApplication`).
   > A API sobe em `http://localhost:8080`.

4. **Abrir as telas** `login.html` e `cadastro.html` no navegador (Live Server ou servidor do IntelliJ).
   As portas liberadas para chamar a API são `5500` e `63342`.

---

## Endpoints

- `POST /users/cadastrar`
- `POST /users/login`

Corpo (JSON): `{"email": "usuario@email.com", "senha": "minhasenha"}`
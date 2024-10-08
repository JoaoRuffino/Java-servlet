# ServUser API  
API REST desenvolvida para gerenciamento de usuários com Java Servlet.

## Header Model
* **Descrição**: Necessário para todas as requisições, menos cadastro e login.
* **Exemplo de Header**:
    ```json
      {
        "Authorization": "Bearer <token>"
      }
    ```

## GET /users
* **Descrição**: Recupera informações de todos os usuários.
* **Controller**: ControllerUser
* **Header**: Necessário header padrão
* **Parâmetros de Corpo**: Nenhum
* **Sucesso**:
  * **Status 200**: Sucesso ao recuperar os dados dos usuários.
  * **Exemplo de Resposta**:
    ```json
    [
      {
        "user_id": 1,
        "email": "user@example.com",
        "username": "user1",
        "cep": "12345678"
      },
      {
        "user_id": 2,
        "email": "test@example.com",
        "username": "user2",
        "cep": "87654321"
      }
    ]
    ```
* **Falhas**:
  * **Status 500**: Erro ao acessar o banco de dados.
  * **Status 401**: Não autorizado (Pode estar faltando o header).

---

## DELETE /users
* **Descrição**: Remove um usuário pelo ID.
* **Controller**: ControllerUser
* **Header**: Necessário header padrão
* **Parâmetros de Corpo**:
  * `user_id`: ID do usuário a ser removido.
* **Sucesso**:
  * **Status 204**: Usuário removido com sucesso (sem conteúdo).
* **Falhas**:
  * **Status 400**: ID do usuário não fornecido.
    ```json
    {
      "message": "User ID is required."
    }
    ```
  * **Status 500**: Erro no banco de dados ao remover usuário.
    ```json
    {
      "message": "Fail delete user."
    }
    ```
  * **Status 401**: Não autorizado (Pode estar faltando o header).

---

## PUT /users
* **Descrição**: Atualiza as informações de um usuário existente.
* **Controller**: ControllerUser
* **Header**: Necessário header padrão
* **Parâmetros de Corpo**:
  * `email`: Novo email do usuário.
  * `username`: Novo nome de usuário.
  * `cep`: Novo CEP do usuário.
  * `user_id`: ID do usuário a ser atualizado (não pode ser alterado).
* **Sucesso**:
  * **Status 204**: Usuário atualizado com sucesso (sem conteúdo).
* **Falhas**:
  * **Status 400**: Informações obrigatórias não fornecidas.
    ```json
    {
      "message": "Missing information."
    }
    ```
  * **Status 500**: Erro no banco de dados ao atualizar usuário.
    ```json
    {
      "message": "Fail update user."
    }
    ```
  * **Status 401**: Não autorizado (Pode estar faltando o header).

---


# Register User API  
API REST para registro de novos usuários com Java Servlet.

## POST /user/register
* **Descrição**: Registra um novo usuário.
* **Controller**: ControllerUser
* **Parâmetros de Corpo**:
  * `email`: Email do usuário.
  * `username`: Nome de usuário.
  * `password`: Senha do usuário (Deve ter entre 4 e 8 caracteres, contendo pelo menos um dígito, uma letra maiúscula e uma letra minúscula).
  * `cep`: CEP do usuário.
* **Sucesso**:
  * **Status 201**: Usuário criado com sucesso.
* **Falhas**:
  * **Status 400**: Informações obrigatórias não fornecidas.
    ```json
    {
      "message": "Missing information."
    }
    ```
  * **Status 400**: Senha inválida (não atende aos requisitos de segurança).
    ```json
    {
      "message": "Password not valid."
    }
    ```
  * **Status 500**: Erro no banco de dados ao criar o usuário.
    ```json
    {
      "message": "Error creating user."
    }
    ```
---
# Login User API  
API REST para registro de novos usuários com Java Servlet.

## POST /user/login
* **Descrição**: Realiza o login do usuário.
* **Controller**: ControllerUser
* **Parâmetros de Corpo**:
  * `email`: Email do usuário.
  * `password`: Senha do usuário.
* **Sucesso**:
  * **Status 204**: Login bem-sucedido.
  * **Exemplo de Resposta**:
    ```json
    {
      "message": "Login successful"
    }
    ```
* **Falhas**:
  * **Status 401**: Credenciais inválidas.
    ```json
    {
      "message": "Invalid credentials."
    }
    ```
  * **Status 500**: Erro no banco de dados.

---
# Primeira API com Java Servlet
API REST desenvolvida para aprendizado pessoal

## GET /users
* Descrição: Recupera informações de usuários.
* Controller: ControllerUser
* Parâmetros Corpo: Nenhum
* Sucesso:
  * Status 200: Sucesso ao recuperar os dados dos usuários.
  * Exemplo:
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
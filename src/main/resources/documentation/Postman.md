# Postman


### Workspaces <br>
[Supervisor](...)


### Curl's

- Cadastrar um novo supervisor.
```
curl --location --request POST 'localhost:8080/api/v1/fresh-products/supervisor' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Weslley",
    "lastName": "Rocha",
    "email": "weslley.rocha@email.com",
    "phone": "12345678901",
    "cpf": "12345678901",
    "cidade": "São Paulo",
    "warehouseSupervisor": "BRSP01"
}'
```

- Consultar o supervisor por ID.
```
curl --location --request GET 'localhost:8080/api/v1/fresh-products/supervisor/1'
```

- Listar todos os supervisores.
```
curl --location --request GET 'localhost:8080/api/v1/fresh-products/supervisor'
```

- Atualizar informações de um supervisor específico.
```
curl --location --request PUT 'localhost:8080/api/v1/fresh-products/supervisor/1' \
--header 'Content-Type: application/json' \
--data-raw '{
        "supersivorId": 1,
        "firstName": "Bianca",
        "lastName": "Polegatti",
        "email": "bianca.polegatti@email.com",
        "phone": "1199999999",
        "cpf": "12345678901",
        "cidade": "São Paulo",
        "warehouseSupervisor": "BRSP02"
    }'
```

- Apagar um supervisor.
```
curl --location --request DELETE 'localhost:8080/api/v1/fresh-products/supervisor/1'
```

- Listar todos os supervisores de um Warehouse.
```
curl --location --request GET 'localhost:8080/api/v1/fresh-products/supervisor/warehouseList/BRSP01'
```

<br><br>

<img src="https://img.icons8.com/material-outlined/24/000000/idea--v1.png"/>  Dica: Como imporat um Curl no Postman

1. Clique na guia "Importar" no canto superior esquerdo.
2. Selecione a opção Texto e cole seu comando CURL.
3. Pressione continuar, import e você terá o comando no seu construtor Postman!
4. Clique em Send para postar o comando.




<br><br><br><br>
<img src="https://img.icons8.com/ios/20/000000/login-rounded.png"/>[ Inicio](/Users/werocha/Documents/BootCamp-Meli/Projeto_Integrador/README.md)
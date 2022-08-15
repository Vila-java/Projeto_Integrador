- Registra novo Cliente
```
curl --location --request POST 'localhost:8080/api/v1/fresh-products/client' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Eduardo",
    "lastName": "Eller Behr"
}'
```

- Atualiza o cliente
```
curl --location --request PUT 'localhost:8080/api/v1/fresh-products/client/5' \
--header 'Content-Type: application/json' \
--data-raw '{
    "firstName": "Eduardo",
    "lastName": "Eller"
}'
```

- Lista completa de clientes
```
curl --location --request GET 'localhost:8080/api/v1/fresh-products/client'
```

- Pesquisar cliente por Id
```
curl --location --request GET 'localhost:8080/api/v1/fresh-products/client/6' \
--data-raw ''
```

- Pesquisar cliente com carrinho
```
curl --location --request GET 'localhost:8080/api/v1/fresh-products/list?storageType=FF'
```

- Deletar cliente
```
curl --location --request DELETE 'localhost:8080/api/v1/fresh-products/client/10' \
--data-raw ''
```
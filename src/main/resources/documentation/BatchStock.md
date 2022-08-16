## BatchStock

- Lista de todos os lotes ordenados por data de vencimento
```
curl --location --request GET 'localhost:8080/api/v1/fresh-products/due-date?intervalDate=100&sectionId=1'
```

- Lista de todos os lotes
L = ordenado por lote
Q = ordenado por quantidade atual
V = ordenado por data de vencimento
```
curl --location --request GET 'localhost:8080/api/v1/fresh-products/list/batch?productId=1&batchOrder=L'
```

- Lista de todos os lotes ordenados por data de vencimento  e por categoria
```
curl --location --request GET 'localhost:8080/api/v1/fresh-products/due-date/list?storageType=RF&order=true&intervalDate=100'
```



<br><br><br><br>
<img src="https://img.icons8.com/ios/20/000000/login-rounded.png"/>[ Inicio](https://github.com/Vila-java/Projeto_Integrador/blob/develop/src/main/resources/documentation/Endpoint_Postman.md)
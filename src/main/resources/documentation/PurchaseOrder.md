## PurchaseOrder

- Pesquisar produtos de um carrinho
```
curl --location --request GET 'localhost:8080/api/v1/fresh-products/orders/1' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderNumber": 2,
    "orderDate": "2022-08-05",
    "section": {
        "sectionCode": "1",
        "warehouseCode": "2"
    },
    "batchStock": [
        {
            "batchNumber": 1,
            "productId": 1,
            "currentTemperature": 1,
            "minimumTemperature": 1,
            "initialQuantity": 1,
            "currentQuantity": 1,
            "manufacturingDate": "2022-08-05",
            "manufacturingTime": "2016-03-16T13:56:39.492",
            "dueDate": "2022-08-05"
        }
    ]
}'
```
<BR>


- Lista completa de produtos
```
curl --location --request GET 'localhost:8080/api/v1/fresh-products'
```
<BR>
    
- Lista completa de produtos filtradas por categoria
```
curl --location --request GET 'localhost:8080/api/v1/fresh-products/list?storageType=FF'
```
<BR>

- Carrinho Aberto/Finalizado
```
curl --location --request PUT 'localhost:8080/api/v1/fresh-products/1' \
--data-raw ''
```
<BR>

- Registrar pedido / lista de produtos
```
curl --location --request POST 'localhost:8080/api/v1/fresh-products/orders' \
--header 'Content-Type: application/json' \
--data-raw '{
    "date": "2022-08-10",
    "buyerId": 1,
    "orderStatus": "ACTIVATED",
    "products": [
        {
            "productId": 1,
            "quantity": 10
        },
        {
            "productId": 2,
            "quantity": 3
        }
    ]
}'
```



<br><br><br><br>
<img src="https://img.icons8.com/ios/20/000000/login-rounded.png"/>[ Inicio](https://github.com/Vila-java/Projeto_Integrador/blob/develop/src/main/resources/documentation/Endpoint_Postman.md)

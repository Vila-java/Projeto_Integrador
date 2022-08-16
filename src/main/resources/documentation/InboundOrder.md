## InboundOrder


- Atualiza um lote.
```
curl --location --request PUT 'localhost:8080/api/v1/fresh-products/inboundorder' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderNumber": 1,
    "orderDate": "2022-08-05",
    "supervisorId": 6,
    "section": {
        "sectionCode": 1,
        "warehouseCode": 1
    },
    "batchStock": [
        {
            "batchNumber": 1,
            "productId": 1,
            "currentTemperature": 20,
            "minimumTemperature": 9,
            "initialQuantity": 10,
            "currentQuantity": 10,
            "manufacturingDate": "2022-08-05",
            "manufacturingTime": "2016-03-16T13:56:39.492",
            "dueDate": "2022-08-05"
        }
    ]
}'
```

- Cadastrar um novo lote.
```
curl --location --request POST 'localhost:8080/api/v1/fresh-products/inboundorder' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderNumber": 1,
    "orderDate": "2022-08-05",
    "supervisorId": 6,
    "section": {
        "sectionCode": 1,
        "warehouseCode": 1
    },
    "batchStock": [
        {
            "productId": 1,
            "currentTemperature": 100,
            "minimumTemperature": 9,
            "initialQuantity": 10,
            "currentQuantity": 10,
            "manufacturingDate": "2022-08-05",
            "manufacturingTime": "2016-03-16T13:56:39.492",
            "dueDate": "2022-08-05"
        }
    ]
}'
```



<br><br><br><br>
<img src="https://img.icons8.com/ios/20/000000/login-rounded.png"/>[ Inicio](https://github.com/Vila-java/Projeto_Integrador)
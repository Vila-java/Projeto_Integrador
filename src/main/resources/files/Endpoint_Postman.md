- Cadastrar novo lote
```
curl --location --request POST 'localhost:8080/api/v1/fresh-products/inboundorder' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderNumber": 1,
    "orderDate": "2022-08-05",
    "supervisorId": 1,
    "section": {
        "sectionCode": 1,
        "warehouseCode": 1
    },
    "batchStock": [
        {
            "productId": 2,
            "currentTemperature": 10,
            "minimumTemperature": 9,
            "initialQuantity": 10,
            "currentQuantity": 10,
            "manufacturingDate": "2022-08-05",
            "manufacturingTime": "2016-03-16T13:56:39.492",
            "dueDate": "2022-08-05"
        },
        {
            "productId": 2,
            "currentTemperature": 10,
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

- Atualizar novo lote
```
curl --location --request PUT 'localhost:8080/api/v1/fresh-products/inboundorder' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderNumber": 1,
    "orderDate": "2022-08-05",
    "supervisorId": 1,
    "section": {
        "sectionCode": 1,
        "warehouseCode": 1
    },
    "batchStock": [
        {
            "productId": 1,
            "currentTemperature": 8,
            "minimumTemperature": 9,
            "initialQuantity": 8,
            "currentQuantity": 8,
            "manufacturingDate": "2022-08-05",
            "manufacturingTime": "2016-03-16T13:56:39.492",
            "dueDate": "2022-08-05"
        },
        {
            "productId": 1,
            "currentTemperature": 9,
            "minimumTemperature": 8,
            "initialQuantity": 9,
            "currentQuantity": 10,
            "manufacturingDate": "2022-08-05",
            "manufacturingTime": "2016-03-16T13:56:39.492",
            "dueDate": "2022-08-05"
        }
    ]
}'
```


- Lista completa de produtos
```
curl --location --request GET 'localhost:8080/api/v1/fresh-products'
```

- Lista completa de produtos filtrados por categoria
```
curl --location --request GET 'localhost:8080/api/v1/fresh-products/list?storageType=FF'
```

- Mostrar produtos no pedido
```
localhost:8080/api/v1/fresh-products/orders/1
```
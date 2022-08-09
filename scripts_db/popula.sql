USE freshfood_db;
INSERT INTO `freshfood_db`.`supervisor`
(`supersivor_id`, `first_name`, `last_name`)
VALUES
    (null, "Bianca", "Polegatti"),
    (null, "Bianca", "Schmitt"),
    (null, "Samantha", "Luchhmann"),
    (null, "Evelin", "Cristina"),
    (null, "Matheus", "Roberto"),
    (null, "Weslley", "Rocha");

INSERT INTO `freshfood_db`.`warehouse`
(`warehouse_id`, `address_code`, `supervisor_id`)
VALUES
    (null, "BRSP01", 6),
    (null, "BRSP02", 1),
    (null, "BRSC01", 2),
    (null, "BRSC02", 4),
    (null, "BRSC03", 3),
    (null, "BRDF01", 5);

INSERT INTO `freshfood_db`.`section`
(`section_id`, `product_capacity`, `storage_type`, `warehouse_id`)
VALUES
    (null, 50, "FROZEN", 1),
    (null, 100, "REFRIGERATED", 1),
    (null, 85, "FRESH", 1),
    (null, 50, "FROZEN", 2),
    (null, 100, "REFRIGERATED", 2),
    (null, 85, "FRESH", 2),
    (null, 50, "FROZEN", 3),
    (null, 100, "REFRIGERATED", 3),
    (null, 85, "FRESH", 3),
    (null, 50, "FROZEN", 4),
    (null, 100, "REFRIGERATED", 4),
    (null, 85, "FRESH", 4),
    (null, 50, "FROZEN", 5),
    (null, 100, "REFRIGERATED", 5),
    (null, 85, "FRESH", 6),
    (null, 50, "FROZEN", 6);

INSERT INTO `freshfood_db`.`batch`
(`batch_number`, `current_quantity`, `current_temperature`, `due_date`, `initial_quantity`,
 `manufacturing_date`, `manufacturing_time`, `minimum_temperature`, `section_id`)
VALUES
    (null, 50, 22, "2022-09-25", 10, "2022-09-01", "2016-03-16T13:56:39.492", 2, 1),
    (null, 30, 20, "2022-10-25", 5, "2022-09-10", "2016-03-16T13:56:39.492", 2, 1),
    (null, 10, 22, "2022-09-25", 10, "2022-09-01", "2016-03-16T13:56:39.492", 2, 1),
    (null, 50, 22, "2022-09-25", 10, "2022-09-01", "2016-03-16T13:56:39.492", 2, 2),
    (null, 30, 22, "2022-09-25", 10, "2022-09-01", "2016-03-16T13:56:39.492", 2, 2),
    (null, 10, 22, "2022-09-25", 10, "2022-09-01", "2016-03-16T13:56:39.492", 2, 2),
    (null, 50, 22, "2022-09-25", 10, "2022-09-01", "2016-03-16T13:56:39.492", 2, 3),
    (null, 30, 22, "2022-09-25", 10, "2022-09-01", "2016-03-16T13:56:39.492", 2, 3),
    (null, 10, 22, "2022-09-25", 10, "2022-09-01", "2016-03-16T13:56:39.492", 2, 3),
    (null, 50, 22, "2022-09-25", 10, "2022-09-01", "2016-03-16T13:56:39.492", 2, 4),
    (null, 30, 22, "2022-09-25", 10, "2022-09-01", "2016-03-16T13:56:39.492", 2, 4),
    (null, 10, 22, "2022-09-25", 10, "2022-09-01", "2016-03-16T13:56:39.492", 2, 4),
    (null, 50, 22, "2022-09-25", 10, "2022-09-01", "2016-03-16T13:56:39.492", 2, 5),
    (null, 30, 22, "2022-09-25", 10, "2022-09-01", "2016-03-16T13:56:39.492", 2, 5),
    (null, 10, 22, "2022-09-25", 10, "2022-09-01", "2016-03-16T13:56:39.492", 2, 6),
    (null, 80, 22, "2022-09-25", 10, "2022-09-01", "2016-03-16T13:56:39.492", 2, 6);

INSERT INTO `freshfood_db`.`seller`
(`seller_id`, `first_name`, `last_name`)
VALUES
    (null, "Iasmin", "Vilaverde"),
    (null, "Olga", "Quirino"),
    (null, "Úria", "Maryam"),
    (null, "Lídia", "Estrada"),
    (null, "Alexa", "Godoi"),
    (null, "Maryam", "Dias");

INSERT INTO `freshfood_db`.`product`
(`product_id`, `description`, `name`, `price`, `storage_type`, `weight`, `seller_id`)
VALUES
    (null, "Frango muito gostoso", "Frango", 22.30, "FROZEN", 20, 1),
    (null, "COXA DE FRANGO", "Frango", 10.90, "FROZEN", 1, 1),
    (null, "FILEZINHO SASSAMI", "Bovinas", 26.96, "REFRIGERATED", 2.3, 1),
    (null, "COSTELA BAFO", "COSTELA", 22.30, "FROZEN", 20, 1),
    (null, "ESPETINHO DE PANCETA SWIFT", "PANCETA", 18.90, "REFRIGERATED", 3, 1),
    (null, "ESPETINHO BOVINO SWIFT", "BOVINO", 39.30, "FRESH", 900, 1),
    (null, "BIFE DE CONTRA FILÉ SWIFT", "BOVINO", 48.30, "FRESH", 4, 1),
    (null, "Baby Picanha Swift", "BOVINO", 98.00, "FROZEN", 7, 1);

INSERT INTO `freshfood_db`.`inbound_order`
(`order_number`, `order_date`, `section_id`, `supervisor_id`)
VALUES
    (null, "2022-09-05", 1, 6),
    (null, "2022-09-10", 2, 5),
    (null, "2022-09-22", 3, 4),
    (null, "2022-09-30", 4, 3),
    (null, "2022-10-03", 6, 2),
    (null, "2022-08-05", 5, 1);

INSERT INTO `freshfood_db`.`client`
(`client_id`, `first_name`, `last_name`)
VALUES
    (null, "Nico", "Lopez"),
    (null, "Jennifer", "Richmond"),
    (null, "Virginia", "Lacombe"),
    (null, "Patti", "Lopez"),
    (null, "Patti", "Thompson"),
    (null, "Jennifer", "Lopez"),
    (null, "Virginia", "Lopez"),
    (null, "Brenda", "Jarvis"),
    (null, "Rocha", "Jarvis");
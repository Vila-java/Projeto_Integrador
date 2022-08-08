USE freshfood_db;
INSERT INTO `freshfood_db`.`supervisor`
(`supersivor_id`, `first_name`, `last_name`)
VALUES
(null, "João", "Kleber");

INSERT INTO `freshfood_db`.`warehouse`
(`warehouse_id`, `address_code`, `supervisor_id`)
VALUES
(null, "SP1", 1);

INSERT INTO `freshfood_db`.`section`
(`section_id`, `product_capacity`, `storage_type`, `warehouse_id`)
VALUES
(null, 20, "REFRIGERATED", 1);

INSERT INTO `freshfood_db`.`batch`
(`batch_number`, `current_quantity`, `current_temperature`, `due_date`, `initial_quantity`,
 `manufacturing_date`, `manufacturing_time`, `minimum_temperature`, `section_id`)
VALUES
(null, 4, 22, "2022-09-25", 10, "2022-09-01", "2022-09-01", 2, 1);

INSERT INTO `freshfood_db`.`seller`
(`seller_id`, `first_name`, `last_name`)
VALUES
(null, "Carlos", "Dias");

INSERT INTO `freshfood_db`.`product`
(`product_id`, `description`, `name`, `price`, `storage_type`, `weight`, `batch_id`, `seller_id`)
VALUES
(null, "Frango muito gostoso", "Frango", 22.30, "REFRIGERATED", 20, 1, 1);

INSERT INTO `freshfood_db`.`inbound_order`
(`order_number`, `order_date`, `section_id`, `supervisor_id`)
VALUES
(null, "2022-09-05", 1, 1);

INSERT INTO `freshfood_db`.`client`
(`client_id`, `first_name`, `last_name`)
VALUES
(null, "Nico", "Lopez");
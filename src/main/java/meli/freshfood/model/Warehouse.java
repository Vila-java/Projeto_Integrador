package meli.freshfood.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Warehouse {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long warehouseId;
    private String addressCode;

}

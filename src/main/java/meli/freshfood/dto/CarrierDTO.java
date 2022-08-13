package meli.freshfood.dto;

import lombok.Getter;
import lombok.Setter;
import meli.freshfood.model.Address;

@Getter
@Setter
public class CarrierDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String cpf;
    private String email;
    private Long phoneNumber;
    private Address address;

}

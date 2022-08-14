package meli.freshfood.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarrierDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String cpf;
    private String email;
    private Long phoneNumber;
    private String zipCode;
    private String address;
    private String number;
    private String neighborhood;
    private String city;
    private String state;

}

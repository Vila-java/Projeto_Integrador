package meli.freshfood.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {

    private String zipCode;
    private String address;
    private String neighborhood;
    private String city;
    private String state;

}

package meli.freshfood.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Carrier {

    //TODO: acrescentar @notnull -> verificar se é ela que engloba espaços em brancos etc
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 10)
    private String firstName;

    @Column(nullable = false, length = 10)
    private String lastName;

    @Column(nullable = false, length = 11)
    private String cpf;

    @Column(nullable = false)
    @Email
    private String email;

    @Column(nullable = false)
    @Min(value = 10)
    private Long phoneNumber;

    private String zipCode;
    private String address;
    private Integer number;
    private String neighborhood;
    private String city;
    private String state;

    @OneToOne
    private Vehicle vehicle;

}

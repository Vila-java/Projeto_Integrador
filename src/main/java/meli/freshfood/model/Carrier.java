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

    @Column(nullable = false, length = 8)
    private String zipCode;

    @Column(nullable = false, length = 10)
    private String address;

    @Column(nullable = false)
    @Min(value = 1)
    private Integer number;

    @Column(nullable = false,length = 10)
    private String neighborhood;

    @Column(nullable = false, length = 10)
    private String city;

    @Column(nullable = false, length = 10)
    private String state;

    @OneToOne
    private Vehicle vehicle;

}

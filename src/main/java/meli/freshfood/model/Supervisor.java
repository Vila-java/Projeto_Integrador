package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long supersivorId;

    @Column(nullable = false, length = 10)
    private String firstName;

    @Column(nullable = false, length = 60)
    private String lastName;

    @Column(nullable = false, length = 50)
    @Email(message = "Favor insira um e-mail valido")
    private String email;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(nullable = false,length = 11)
    private String cpf;

    @Column(nullable = false, length = 20)
    private String cidade;

    @OneToOne(mappedBy = "supervisor")
    @JsonIgnore()
    private Warehouse warehouse;

    @OneToMany(mappedBy = "supervisor")
    @JsonIgnore()
    private List<InboundOrder> inboundOrders;
}

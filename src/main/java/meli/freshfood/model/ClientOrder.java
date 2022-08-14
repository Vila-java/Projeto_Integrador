package meli.freshfood.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import meli.freshfood.dto.BatchDTO;
import meli.freshfood.dto.ClientOrderDTO;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ClientOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "clientOrder")
    @JsonIgnore()
    Set<ProductClientOrder> productClientOrder;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(columnDefinition = "date", nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore()
    private Client client;

    @Enumerated(EnumType.STRING)
    private StatusOrder orderStatus;

    public ClientOrder(Set<ProductClientOrder> productClientOrder, LocalDate date, Client client, StatusOrder orderStatus) {
        this.productClientOrder = productClientOrder;
        this.date = date;
        this.client = client;
        this.orderStatus = orderStatus;
    }

    public ClientOrderDTO toDTO() {
        return new ClientOrderDTO(this.productClientOrder, this.date, this.orderStatus);
    }

}

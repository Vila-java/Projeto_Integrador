package meli.freshfood.model;

import lombok.Data;
import org.springframework.boot.autoconfigure.session.StoreType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sectionId;
    private StoreType storageType;
    private Long productCapacity;
}

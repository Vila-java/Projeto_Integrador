package meli.freshfood.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
public abstract class User {

    @Column(nullable = false, length = 10)
    private String firstName;

    @Column(nullable = false, length = 60)
    private String lastName;
}

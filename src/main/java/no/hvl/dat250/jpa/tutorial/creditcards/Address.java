package no.hvl.dat250.jpa.tutorial.creditcards;

import java.util.Collection;
import jakarta.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private Integer number;

    @ManyToMany(mappedBy = "addresses")
    private Collection<Customer> owners;

    public String getStreet() {
        // DONE: implement method!
        return street;
    }

    public Integer getNumber() {
        // DONE: implement method!
        return number;
    }

    public Collection<Customer> getOwners() {
        // DONE: implement method!
        return owners;
    }
}

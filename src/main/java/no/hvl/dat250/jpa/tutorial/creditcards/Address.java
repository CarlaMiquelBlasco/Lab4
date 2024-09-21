package no.hvl.dat250.jpa.tutorial.creditcards;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;
    private Integer number;

    @ManyToMany(mappedBy = "addresses")
    private Set<Customer> owners;

    public String getStreet() {
        // DONE: implement method!
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getNumber() {
        // DONE: implement method!
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Set<Customer> getOwners() {
        // DONE: implement method!
        return owners;
    }

    public void setOwners(Set<Customer> owners) {
        this.owners = owners;
    }
}

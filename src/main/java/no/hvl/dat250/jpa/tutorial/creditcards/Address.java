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

    public Collection<Customer> getOwners() {
        // DONE: implement method!
        return owners;
    }

    public void setOwners(Collection<Customer> owners) {
        this.owners = owners;
    }
}

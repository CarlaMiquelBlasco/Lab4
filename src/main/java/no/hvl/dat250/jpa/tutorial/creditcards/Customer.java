package no.hvl.dat250.jpa.tutorial.creditcards;

import java.util.Collection;
import jakarta.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany
    @JoinTable(name = "customer_address",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Collection<Address> addresses;

    @ManyToMany
    @JoinTable(name = "customer_creditcard",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "creditcard_id"))
    private Collection<CreditCard> creditCards;

    public String getName() {
        // DONE: implement method!
        return name;
    }

    public Collection<Address> getAddresses() {
        // DONE: implement method!
        return addresses;
    }

    public Collection<CreditCard> getCreditCards() {
        // DONE: implement method!
        return creditCards;
    }
}

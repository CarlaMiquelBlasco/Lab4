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

    public void setName(String name) {
        this.name = name;
    }

    public Collection<Address> getAddresses() {
        // DONE: implement method!
        return addresses;
    }

    public void setAddresses(Collection<Address> addresses) {  // Corrected the parameter name
        this.addresses = addresses;
    }

    public Collection<CreditCard> getCreditCards() {
        // DONE: implement method!
        return creditCards;
    }

    public void setCreditCards(Collection<CreditCard> creditCards) {  // Corrected parameter name
        this.creditCards = creditCards;
    }
}

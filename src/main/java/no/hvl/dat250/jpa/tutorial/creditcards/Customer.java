package no.hvl.dat250.jpa.tutorial.creditcards;

import java.util.Set;
import jakarta.persistence.*;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @ManyToMany //(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "customer_address",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")) ///Explicit Join Table for convenience, although it is automatically created
    private Set<Address> addresses;

    @ManyToMany //(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "customer_creditcard",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "creditcard_id")) //Explicit Join Table for convenience, although it is automatically created
    private Set<CreditCard> creditCards;

    public String getName() {
        // DONE: implement method!
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Address> getAddresses() {
        // DONE: implement method!
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {  // Corrected the parameter name
        this.addresses = addresses;
    }

    public Set<CreditCard> getCreditCards() {
        // DONE: implement method!
        return creditCards;
    }

    public void setCreditCards(Set<CreditCard> creditCards) {  // Corrected parameter name
        this.creditCards = creditCards;
    }
}

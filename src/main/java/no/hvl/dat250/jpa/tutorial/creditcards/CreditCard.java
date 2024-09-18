package no.hvl.dat250.jpa.tutorial.creditcards;

import jakarta.persistence.*;

@Entity
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;
    private Integer creditLimit;
    private Integer balance;

    @ManyToOne
    private Pincode pincode;

    @ManyToOne
    private Bank owningBank;

    public Integer getNumber() {
        // DONE: implement method!
        return number;
    }

    public Integer getBalance() {
        // DONE: implement method!
        return balance;
    }

    public Integer getCreditLimit() {
        // DONE: implement method!
        return creditLimit;
    }

    public Pincode getPincode() {
        // DONE: implement method!
        return pincode;
    }

    public Bank getOwningBank() {
        // DONE: implement method!
        return owningBank;
    }
}

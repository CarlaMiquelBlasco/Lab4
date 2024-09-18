package no.hvl.dat250.jpa.tutorial.creditcards;

import jakarta.persistence.*;
import java.util.Collection;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "owningBank")
    private Collection<CreditCard> ownedCards;

    public Long getId() {
        return id;
    }

    public String getName() {
        // DONE: implement method!
        return name;
    }

    public Collection<CreditCard> getOwnedCards() {
        // DONE: implement method!
        return ownedCards;
    }
}

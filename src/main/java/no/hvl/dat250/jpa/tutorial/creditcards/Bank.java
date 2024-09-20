package no.hvl.dat250.jpa.tutorial.creditcards;

import jakarta.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "owningBank")
    private Set<CreditCard> ownedCards;

    public Long getId() {
        return id;
    }

    public String getName() {
        // DONE: implement method!
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<CreditCard> getOwnedCards() {
        // DONE: implement method!
        return ownedCards;
    }
}

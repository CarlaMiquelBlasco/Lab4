package no.hvl.dat250.jpa.tutorial.creditcards.driver;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import no.hvl.dat250.jpa.tutorial.creditcards.Customer;
import no.hvl.dat250.jpa.tutorial.creditcards.Address;
import no.hvl.dat250.jpa.tutorial.creditcards.Bank;
import no.hvl.dat250.jpa.tutorial.creditcards.CreditCard;
import no.hvl.dat250.jpa.tutorial.creditcards.Pincode;
import java.util.Set;


public class CreditCardsMain {

  static final String PERSISTENCE_UNIT_NAME = "jpa-tutorial";


  public static void main(String[] args) {
    try (EntityManagerFactory factory = Persistence.createEntityManagerFactory(
        PERSISTENCE_UNIT_NAME); EntityManager em = factory.createEntityManager()) {
      em.getTransaction().begin();
      createObjects(em);
      em.getTransaction().commit();
    }

  }

  private static void createObjects(EntityManager em) {
    // DONE: Create object world as shown in the README.md.
    // Create Customer
    Customer customer = new Customer();
    customer.setName("Max Mustermann");

    // Create Address
    Address address = new Address();
    address.setStreet("Inndalsveien");
    address.setNumber(28);

    // Link Address to Customer (ManyToMany)
    customer.setAddresses(Set.of(address)); // Set in customer
    address.setOwners(Set.of(customer));    // Set in address

    // Create Pincode (shared by both CreditCards)
    Pincode pincode = new Pincode();
    pincode.setPincode("123");
    pincode.setCount(1);

    // Create Bank
    Bank bank = new Bank();
    bank.setName("Pengebank");

    // Create First CreditCard
    CreditCard creditCard1 = new CreditCard();
    creditCard1.setNumber(12345);
    creditCard1.setBalance(-5000);
    creditCard1.setCreditLimit(-10000);
    creditCard1.setPincode(pincode);  // Link the shared pincode
    creditCard1.setOwningBank(bank);  // Link the bank

    // Create Second CreditCard
    CreditCard creditCard2 = new CreditCard();
    creditCard2.setNumber(123);
    creditCard2.setBalance(1);
    creditCard2.setCreditLimit(2000);
    creditCard2.setPincode(pincode);  // Same pincode as creditCard1
    creditCard2.setOwningBank(bank);  // Same bank as creditCard1

    // Link CreditCards to Customer (ManyToMany)
    customer.setCreditCards(Set.of(creditCard1, creditCard2));

    // Persist all entities
    em.persist(customer);   // Customer is the owning side, persist customer and all cascades
    em.persist(address);    // Persist the linked address
    em.persist(pincode);    // Persist the pincode (shared by both credit cards)
    em.persist(bank);       // Persist the bank
    em.persist(creditCard1); // Persist both credit cards
    em.persist(creditCard2);
  }
}

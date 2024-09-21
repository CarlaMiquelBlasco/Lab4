package no.hvl.dat250.jpa.tutorial.creditcards;

import jakarta.persistence.*;

@Entity
public class Pincode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pincode;
    private Integer count;

    public Long getId() {
        return id;
    }

    public String getCode() {
        // DONE: implement method!
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Integer getCount() {
        // DONE: implement method!
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

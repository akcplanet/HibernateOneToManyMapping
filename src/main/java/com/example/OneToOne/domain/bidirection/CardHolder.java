package com.example.OneToOne.domain.bidirection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class CardHolder {
    @Id
    @Column(name = "ch_id")
    private Long id;

    private String email;
    private String password;

    @OneToOne(mappedBy = "cardHolder", cascade = CascadeType.ALL, optional = false)
    private CardHolderDetails cardHolderDetails;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CardHolderDetails getCardHolderDetails() {
        return cardHolderDetails;
    }

    public void setCardHolderDetails(CardHolderDetails cardHolderDetails) {
        this.cardHolderDetails = cardHolderDetails;
    }

    public void setId(Long id) {
        this.id = id;
    }
}


package com.example.OneToOne.domain.bidirection;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *  One-to-one association from an embeddable class to another entity.
 */
@Entity
public class Staff {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private LocationDetails location;

    public LocationDetails getLocation() {
        return location;
    }

    public void setLocation(LocationDetails location) {
        this.location = location;
    }

    public Long getId() {
        return id;
    }
}

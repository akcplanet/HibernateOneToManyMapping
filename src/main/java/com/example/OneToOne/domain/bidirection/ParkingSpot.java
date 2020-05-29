package com.example.OneToOne.domain.bidirection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class ParkingSpot {
    @Id
    @GeneratedValue
    private Long id;
    String garage;

    @OneToOne(mappedBy = "location.parkingSpot")
    private Staff assignedTo;


    public String getGarage() {
        return garage;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    public Staff getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Staff assignedTo) {
        this.assignedTo = assignedTo;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

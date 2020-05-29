package com.example.OneToOne.domain.bidirection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

/**
 * One-to-one association that maps a foreign key column
 */
@Entity
public class Customer {
    @Id
    private Long customerId;

    private String email;

    @OneToOne(cascade= CascadeType.ALL, optional=false)
    @JoinColumn(name="customer_record_id", unique=true, nullable=false, updatable=false)
    private CustomerRecord customerRecord;

    public CustomerRecord getCustomerRecord() {
        return customerRecord;
    }

    public void setCustomerRecord(CustomerRecord customerRecord) {
        this.customerRecord = customerRecord;
        customerRecord.setCustomer(this);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}

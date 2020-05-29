package com.example.OneToOne.domain.bidirection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CustomerRecord {

    @Id
    private Long customerRecordId;

    private String billingInformation;

    @OneToOne(mappedBy="customerRecord", optional=false)
    private Customer customer;

    public Customer getCustomer() { return customer; }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setBillingInformation(String billingInformation) {
        this.billingInformation = billingInformation;
    }

    public void setCustomerRecordId(Long customerRecordId) {
        this.customerRecordId = customerRecordId;
    }
}

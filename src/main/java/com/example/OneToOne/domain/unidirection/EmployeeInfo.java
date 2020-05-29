package com.example.OneToOne.domain.unidirection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EmployeeInfo {
    @Id
    private Long employeeInfoId;

    private String jobTitle;

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setEmployeeInfoId(Long employeeInfoId) {
        this.employeeInfoId = employeeInfoId;
    }
    
    
    
}

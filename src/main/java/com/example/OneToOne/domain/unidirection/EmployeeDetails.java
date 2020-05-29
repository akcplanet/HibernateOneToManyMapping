package com.example.OneToOne.domain.unidirection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

/**
 * One-to-one association where both source and target share the same primary key values.
 */
@Entity
public class EmployeeDetails {
    @Id
    @Column(name = "employee_id")
    private Long employeeId;

    private String name;

    @OneToOne(orphanRemoval = true)
    @MapsId
    EmployeeInfo info;

    public void setInfo(EmployeeInfo info) {
        this.info = info;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

	public String getName() {
		return name;
	}

	public EmployeeInfo getInfo() {
		return info;
	}
    
    
}

package com.example.OneToOne.domain.bidirection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class MainCustomer  {


	static Session sessionObj;
	static SessionFactory sessionFactoryObj;

	private static SessionFactory buildSessionFactory() {
		// Creating Configuration Instance & Passing Hibernate Configuration File
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");

		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();

		// Creating Hibernate SessionFactory Instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;
	}

 
	  public static void main(String[] args) {
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        customer.setEmail("jack@gmail.com");

        CustomerRecord customerRecord = new CustomerRecord();
        customerRecord.setCustomerRecordId(2L);
        customerRecord.setBillingInformation("123 Main Street, Somewhere, CA 94000");
        customer.setCustomerRecord(customerRecord);
        
        sessionObj = buildSessionFactory().openSession();

		System.out.println("Starting 2 Many to Many");

		sessionObj.beginTransaction();
		sessionObj.save(customer);

		Customer employee1 = (Customer) sessionObj.get(Customer.class, 1L);
		if (employee1 != null) {
			System.out.println(employee1.getEmail());
		}
        
		sessionObj.getTransaction().commit();
		sessionObj.close();
  

        //customerRepository.delete(customer);
    }
}

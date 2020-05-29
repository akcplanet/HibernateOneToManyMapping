package com.example.OneToOne.domain.bidirection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class MainCardHolder {
 
	
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
        CardHolder cardHolder = new CardHolder();
        cardHolder.setId(1L);
        cardHolder.setEmail("jacky@gmail.com");
        cardHolder.setPassword("password1");

        CardHolderDetails cardHolderDetails = new CardHolderDetails();
        cardHolderDetails.setName("Lucky Luck");
        cardHolderDetails.setDateOfBirth("10/10/1970");
        cardHolderDetails.setPhoneNumber("001 9383 93838");
        cardHolderDetails.setCardHolder(cardHolder); // Important

        cardHolder.setCardHolderDetails(cardHolderDetails);
        
        sessionObj = buildSessionFactory().openSession();

		System.out.println("Starting 2 Many to Many");

		sessionObj.beginTransaction();
		sessionObj.save(cardHolder);

		CardHolder employee1 = (CardHolder) sessionObj.get(CardHolder.class, 1L);
		if (employee1 != null) {
			System.out.println(employee1.getCardHolderDetails());
		}

		// sessionObj.delete(employee);
		sessionObj.getTransaction().commit();
		sessionObj.close();
       
        // Can not delete cardHolder b/c CardHolderDetails depends on
        // it by sharing primary key. Try to delete CardHolderDetails first,
        // then for cardHolder.
        // cardHolderRepository.delete(cardHolder);
    }
}

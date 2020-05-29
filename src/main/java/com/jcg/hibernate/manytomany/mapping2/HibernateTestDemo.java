package com.jcg.hibernate.manytomany.mapping2;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateTestDemo {

    /**
     * @param args
     */
	
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
	
    public static void main(String[] args)
    {
        UserDetails user = new UserDetails();
        UserDetails user2 = new UserDetails();
        
        Vehicle vehicle = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        
        vehicle.setVehicleName("Car");
        vehicle.getUser().add(user);
        vehicle.getUser().add(user2);
        
        vehicle2.setVehicleName("Jeep");
        vehicle2.getUser().add(user2);
        vehicle2.getUser().add(user);
        
        user.setUserName("First User");
        user2.setUserName("Second User");
        user.getVehicle().add(vehicle);
        user.getVehicle().add(vehicle2);
        user2.getVehicle().add(vehicle);
        user2.getVehicle().add(vehicle2);
        
        sessionObj = buildSessionFactory().openSession();

		System.out.println("Starting 2 Many to Many");

	
		sessionObj.beginTransaction();
		sessionObj.save(vehicle);
		sessionObj.save(vehicle2);
		sessionObj.save(user);
		sessionObj.save(user2);
		sessionObj.getTransaction().commit();
		sessionObj.close();
    }
}
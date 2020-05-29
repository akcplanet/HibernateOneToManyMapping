package com.example.OneToOne.domain.bidirection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class MainStaff  {

	
	
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

        LocationDetails location = new LocationDetails();
        location.setOfficeNumber(911);
        ParkingSpot parkingSpot = new ParkingSpot();
        parkingSpot.setGarage("Hilton Garage");
        location.setParkingSpot(parkingSpot);

        Staff staff = new Staff();
        staff.setLocation(location);
        sessionObj = buildSessionFactory().openSession();

		System.out.println("Starting 2 Many to Many");

		sessionObj.beginTransaction();
		sessionObj.save(staff);

		Staff employee1 = (Staff) sessionObj.get(Staff.class, 1L);
		if (employee1 != null) {
			System.out.println(employee1.getLocation());
		}

	   
		sessionObj.getTransaction().commit();
		sessionObj.close();

        /*Staff staff1 = staffRepository.findById(15L)
                .orElseThrow(() -> new EntityNotFoundException());
        System.out.println("Staff Identifier: " + staff1.getId());
        System.out.println("Staff Office No.: " + staff1.getLocation().getOfficeNumber());
        System.out.println("Staff Garage Name: " + staff1.getLocation().getParkingSpot().getGarage() );*/

    }
}

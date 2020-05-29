package com.example.OneToOne.domain.unidirection;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class MainEmployee {

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
		EmployeeDetails employee = new EmployeeDetails();
		// employee.setEmployeeId(1L); error
		employee.setName("Peter T");

		EmployeeInfo employeeInfo = new EmployeeInfo();
		employeeInfo.setEmployeeInfoId(2L);
		employeeInfo.setJobTitle("Developer");
		employee.setInfo(employeeInfo);
		sessionObj = buildSessionFactory().openSession();

		System.out.println("Starting 2 Many to Many");

		sessionObj.beginTransaction();
		sessionObj.save(employee);

		EmployeeDetails employee1 = (EmployeeDetails) sessionObj.get(EmployeeDetails.class, 2L);
		if (employee1 != null) {
			System.out.println(employee1.getEmployeeId());
		}

	      sessionObj.delete(employee);
		sessionObj.getTransaction().commit();
		sessionObj.close();
	}
}

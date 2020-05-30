package com.jcg.hibernate.manytomany.mapping;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Main {

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

		addNewMasterAndDetail();

	}

	private static void addNewMasterAndDetail() {
		Transaction transaction = null;
		sessionObj = buildSessionFactory().openSession();

		System.out.println("Starting Many to Many");

		transaction = sessionObj.getTransaction();
        transaction.begin();

		Employee e1 = new Employee("Prasad Kharkar");

		Employee e2 = new Employee("Taware");

		Project p1 = new Project("Ecommerce");
		
		Project p2 = new Project("Tracking");


		
		/* List<Employee> emp = new ArrayList<Employee>(); */
		/*
		 * emp.add(e1); emp.add(e2);
		 */
		  
	
		p1.getEmployees().addAll(new ArrayList<Employee>() {
			private static final long serialVersionUID = 1L;
			{
				add(new Employee("Book A1"));
				add(new Employee("Book A2"));
			//	add(new Employee("Book A3"));
			}
		});
		p2.getEmployees().addAll(new ArrayList<Employee>() {
			private static final long serialVersionUID = 1L;
			{
				add(new Employee("Book A4"));
				add(new Employee("Book A5"));
			//	add(new Employee("Book A6"));
			}
		});
		 

		e1.getProjects().addAll(new ArrayList<Project>() { 
			private static final long serialVersionUID = 1L;
			{
			add(new Project("Project P1"));
			add(new Project("Project P2"));
			add(new Project("Project P3"));
		}});


		e2.getProjects().addAll(new ArrayList<Project>() {
			private static final long serialVersionUID = 1L;
			{
			add(new Project("Project P4"));
			add(new Project("Project P5"));
			add(new Project("Project P6"));
		}});

	//	sessionObj.save(e1);
	//    sessionObj.save(p4);
	//	sessionObj.save(p5);
	//	sessionObj.save(p6);
	//.save(e2);
		sessionObj.save(p1);
   	sessionObj.save(p2);

		transaction.commit();
		 sessionObj.clear();

		Employee employeeEntity = (Employee) sessionObj.get(Employee.class, 1);

		Project projectEntity = (Project) sessionObj.get(Project.class, 1);

		if (employeeEntity != null) {
			System.out.println(employeeEntity.getName());
		}

		if (projectEntity != null) {
			System.out.println(projectEntity.getName());
		}

		System.out.println("\n.......Records Saved Successfully To The Database.......");
		sessionObj.close();
		sessionFactoryObj.close();

	}
}

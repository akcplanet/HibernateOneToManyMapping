package com.jcg.hibernate.manytomany.mapping;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
		sessionObj = buildSessionFactory().openSession();

		System.out.println("Starting Many to Many");

		sessionObj.beginTransaction();

		Employee e1 = new Employee();
		e1.setName("Prasad Kharkar");

		Employee e2 = new Employee();
		e2.setName("Taware");

		Project p1 = new Project();
		p1.setName("Ecommerce");

		Project p2 = new Project();
		p2.setName("Tracking");

		
		  List<Employee> emp = new ArrayList<Employee>(); 
		/*
		 * emp.add(e1); emp.add(e2);
		 */
		  
	
		 

		p1.setEmployees(new ArrayList<Employee>() {
			private static final long serialVersionUID = 1L;
			{
				add(new Employee("Book A1"));
				add(new Employee("Book A2"));
				add(new Employee("Book A3"));
			}
		});
		p2.setEmployees(new ArrayList<Employee>() {
			private static final long serialVersionUID = 1L;
			{
				add(new Employee("Book A4"));
				add(new Employee("Book A5"));
				add(new Employee("Book A6"));
			}
		});

		  List<Project> projects = new ArrayList<Project>(); 
		 Project p4 = new Project("Project P1");
		 Project p5 = new Project("Project P2");
		 Project p6 = new Project("Project P3");
		
		 projects.add(p4);
		projects.add(p5);
		projects.add(p6);

		e1.setProjects(projects);

		final Project p7 = new Project("Project P4");
		final Project p8 = new Project("Project P5");
		final Project p9 = new Project("Project P6");

		e2.setProjects(new ArrayList<Project>() {
			{
				add(p7);
				add(p8);
				add(p9);
			}
		});

		sessionObj.save(e1);
	    sessionObj.save(p4);
		sessionObj.save(p5);
		sessionObj.save(p6);
	//	sessionObj.save(e2);
//		sessionObj.save(p1);
//		sessionObj.save(p2);

		sessionObj.getTransaction().commit();
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

		/*
		 * EntityManager em = emf.createEntityManager();
		 * System.out.println("Starting ManytoMany");
		 * 
		 * 
		 * em.getTransaction().begin(); EntityTransaction transaction =
		 * em.getTransaction(); transaction.begin();
		 * 
		 * 
		 * Employee prasad = new Employee(); prasad.setName("prasad kharkar");
		 * 
		 * Employee harish = new Employee(); harish.setName("Harish taware");
		 * 
		 * Project ceg = new Project(); ceg.setName("CEG");
		 * 
		 * Project gtt = new Project(); gtt.setName("GTT");
		 * 
		 * List<Project> projects = new ArrayList<Project>(); projects.add(ceg);
		 * projects.add(gtt);
		 * 
		 * List<Employee> employees = new ArrayList<Employee>(); employees.add(prasad);
		 * employees.add(harish);
		 * 
		 * ceg.setEmployees(employees); gtt.setEmployees(employees);
		 * 
		 * prasad.setProjects(projects); harish.setProjects(projects);
		 * 
		 * em.persist(prasad); em.persist(harish); em.persist(ceg); em.persist(gtt);
		 */

		// transaction.commit();
		sessionObj.close();
		sessionFactoryObj.close();

	}
}

package com.jcg.hibernate.onetomany.mapping;


import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class AppMain {

	static Session sessionObj;
	static SessionFactory sessionFactoryObj;

	private static SessionFactory buildSessionFactory() {
		// Creating Configuration Instance & Passing Hibernate Configuration File
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");

		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder().applySettings(configObj.getProperties()).build(); 

		// Creating Hibernate SessionFactory Instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;
	}

	public static void main(String[] args) {
		System.out.println(".......Hibernate One To Many Mapping Example.......\n");
		Transaction tx = null;
		try {
			sessionObj = buildSessionFactory().openSession();
			tx=	sessionObj.beginTransaction();
			
			Student studentObj = new Student("Java1", "Geek1",  "j1avageek@javacodegeeks.com", "01123456789");
			sessionObj.save(studentObj);

			MarksDetails marksObj1 = new MarksDetails("English1", "100", "90",  "Pass");  
			marksObj1.setStudent(studentObj);  
			sessionObj.save(marksObj1);

			MarksDetails marksObj2 = new MarksDetails("Maths1", "100", "99",  "Pass");  
			marksObj2.setStudent(studentObj);
			sessionObj.save(marksObj2);

			MarksDetails marksObj3 = new MarksDetails("Science1", "100", "94",  "Pass");  
			marksObj3.setStudent(new Student("AmitPre", "Geek1",  "j1avageek@javacodegeeks.com", "01123456789"));
			sessionObj.save(marksObj3);

			// Committing The Transactions To The Database
			tx.commit();
		//	sessionObj.flush();
	    	sessionObj.clear();
		//	sessionObj.evict(marksObj2);
			MarksDetails readerEntity = (MarksDetails) sessionObj.get(MarksDetails.class, 2L);
			Student subscriptionEntity = (Student) sessionObj.get(Student.class, 2L);
		
	        if (readerEntity != null) {
	           System.out.println(readerEntity.getResult());
	        }
	        

	        if (subscriptionEntity != null) {
	           System.out.println(subscriptionEntity.getFirstName());
	        }
	        

			System.out.println("\n.......Records Saved Successfully To The Database.......");
		
		
		} catch(Exception sqlException) {
			if(null != sessionObj.getTransaction()) {
				System.out.println("\n.......Transaction Is Being Rolled Back.......");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if(sessionObj != null) {
				sessionObj.close();
			}
		}
	}
}
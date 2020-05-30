package org.alexis.manytomany.hibernate;

import java.util.ArrayList;


import org.hibernate.Session;
import org.hibernate.Transaction;


/**
 * @author imssbora
 */
public class MainApp {
   public static void main(String[] args) {
      Session session = null;
      Transaction transaction = null;
      try {
         session = HibernateUtil.getSessionFactory().openSession();
         transaction = session.getTransaction();
         transaction.begin();

         Address address1 = new Address("Bengaluru", "Karnataka", "India", "560016");
         address1.getEmployees().addAll(new ArrayList<Employee>() {
 			private static final long serialVersionUID = 1L;
 			{
 				add(new Employee("Amit Singh", "Tech Manager", 950000));
 				add(new Employee("Sumit Singh", "Office Manager", 750000));
 				add(new Employee("Ravindra Singh", "Sales Manager", 450000));
 			}
 		});
         
         
         Address address2 = new Address("Jaipur", "Rajasthan", "India", "302017");
         
         address2.getEmployees().addAll(new ArrayList<Employee>() {
  			private static final long serialVersionUID = 1L;
  			{
  				add(new Employee("Mohit Sharma", "Software Engineer", 1850000));
  				add(new Employee("ABCD Singh", "Engi Manager", 2750000));
  				add(new Employee("EFGH Singh", "Math Manager", 3450000));
  			}
  		});

         // Employee1 have 2 addresses
         Employee employee1 = new Employee("Ravindra Singh", "Sales Manager", 450000);      
         employee1.getAddresses().addAll(new ArrayList<Address>() {
			private static final long serialVersionUID = 1L;
			{
				add(new Address("Bengaluru", "Karnataka", "India", "560016"));
				add(new Address("Jaipur", "Rajasthan", "India", "302017"));
				add(new Address("Delhi", "Delhi", "India", "902017"));
			}
		});
         
        // employee1.getAddresses().add(new Address("Bengaluru", "Karnataka", "India", "560016"));
       //  employee1.getAddresses().add(new Address("Jaipur", "Rajasthan", "India", "302017"));

         // Employee2 have 1 address
         Employee employee2 = new Employee("Mohit Sharma", "Software Engineer", 850000);
         employee2.getAddresses().add(new Address("Bengaluru", "Karnataka", "India", "560016"));

        session.save(employee1);
        session.save(employee2);
     //    session.save(address1);
    //    session.save(address2);
         transaction.commit();
        session.clear();
         Employee employeeEntity = (Employee) session.get(Employee.class, 1L);

         Address addressEntity = (Address) session.get(Address.class, 1L);

 		if (employeeEntity != null) {
 			System.out.println(employeeEntity.getName());
 		}

 		if (addressEntity != null) {
 			System.out.println(addressEntity.getCity());
 		}

 		System.out.println("\n.......Records Saved Successfully To The Database.......");

         System.out.println("Records saved successfully");

      } catch (Exception e) {
         if (transaction != null) {
            System.out.println("Transaction is being rolled back.");
            transaction.rollback();
         }
         e.printStackTrace();
      } finally {
         if (session != null) {
            session.close();
         }
      }
      HibernateUtil.shutdown();
   }
}

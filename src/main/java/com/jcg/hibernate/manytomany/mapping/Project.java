package com.jcg.hibernate.manytomany.mapping;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name="project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
		private int idproject;
		private String name;
	

		public Project() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Project(String name) {
			super();
			this.name = name;
		}

		@ManyToMany(cascade = CascadeType.ALL,mappedBy="projects" ,fetch=FetchType.LAZY)
		private List<Employee> employees = new ArrayList<Employee>();

		public int getIdproject() {
			return idproject;
		}

		public void setIdproject(int idproject) {
			this.idproject = idproject;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public List<Employee> getEmployees() {
			return employees;
		}

		public void setEmployees(List<Employee> employees) {
			this.employees = employees;
		}

		@Override
		public String toString() {
			return "Project [idproject=" + idproject + ", name=" + name + ", employees=" + employees
					+ ", getIdproject()=" + getIdproject() + ", getName()=" + getName() + ", getEmployees()="
					+ getEmployees() + "]";
		}

}

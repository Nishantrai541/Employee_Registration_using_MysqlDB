package com.employeeRegistration.DAO;

import java.time.LocalDate;

public class EmployeeRegistrationDAO {

	
	private String name;
	private LocalDate joiningDate;
	private LocalDate empDOB;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getJoiningDate() {
		return joiningDate;
	}
	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}
	public LocalDate getEmpDOB() {
		return empDOB;
	}
	public void setEmpDOB(LocalDate empDOB) {
		this.empDOB = empDOB;
	}
	
	
}

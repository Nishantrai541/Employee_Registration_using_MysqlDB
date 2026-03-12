package com.employeeRegistration.Model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="employeeTable")
public class EmployeeModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String name;
	
	@Column(nullable=false)
	private LocalDate dob;
	
	@Column(nullable=false)
	private LocalDate dateOfJoining;
	
	

	public EmployeeModel() {
		super();
	}



	public EmployeeModel(Long id, String name, LocalDate dob, LocalDate dateOfJoining) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.dateOfJoining = dateOfJoining;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public LocalDate getDob() {
		return dob;
	}


	public void setDob(LocalDate dob) {
		this.dob = dob;
	}


	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}


	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

		
		
	
}

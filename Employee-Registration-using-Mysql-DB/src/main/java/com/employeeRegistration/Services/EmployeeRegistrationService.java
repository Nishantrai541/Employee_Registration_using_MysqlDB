package com.employeeRegistration.Services;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employeeRegistration.DAO.EmployeeRegistrationDAO;
import com.employeeRegistration.DAO.EmployeeUpdateDAO;
import com.employeeRegistration.Model.EmployeeModel;
import com.employeeRegistration.Repository.EmployeeRepo;

@Service
public class EmployeeRegistrationService {


	private EmployeeRepo employeeRepo;
	
	public EmployeeRegistrationService(EmployeeRepo employeeRepo) {
		super();
		this.employeeRepo = employeeRepo;
	}

	
	//Method for Registering Employee
	public ResponseEntity<EmployeeModel> addEmployee(EmployeeRegistrationDAO employeeDetails) throws Exception{
		
		if(employeeDetails == null ||
			       employeeDetails.getName() == null || employeeDetails.getName().trim().isEmpty() ||
			       employeeDetails.getJoiningDate() == null ||
			       employeeDetails.getEmpDOB() == null) {		
		throw new Exception("All Field required");
		}
		LocalDate todayDate=LocalDate.now();
		Period empAge=Period.between(employeeDetails.getEmpDOB(), todayDate);
		
		if(empAge.getYears()<18 || empAge.getYears()>=80) {
			throw new Exception("Required Employee minimum age is 18 and maximum age is 80");
		}
		if(employeeDetails.getJoiningDate().isAfter(todayDate)) {
			throw new Exception("Joining date is not greater that today date");
		}
		
		EmployeeModel emp=new EmployeeModel();
		emp.setName(employeeDetails.getName().trim());
		emp.setDob(employeeDetails.getEmpDOB());
		emp.setDateOfJoining(employeeDetails.getJoiningDate());
		emp=employeeRepo.save(emp);		
		if(emp==null) {
			throw new Exception("Something Went Wrong");
		}else {
			return ResponseEntity.status(HttpStatus.CREATED).body(emp);
		}		
		
	}

	

	//Method for Get Employee By employee ID
	public ResponseEntity<EmployeeModel> getEmployeeById(Long id) throws Exception {
				
		if(id==null) {
			throw new Exception("Employee id is required");
		}
		EmployeeModel emp=employeeRepo.findById(id).orElse(null);
		if(emp==null) {
			throw new Exception("There is no Employee is created whith this employee id:"+id);
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(emp);
		}
	}

	

	//Method for Get EmployeeBy Name
	public ResponseEntity<List<EmployeeModel>> getEmployeeByName(String name) throws Exception {
		
		if(name==null || (name!=null && name.trim().isEmpty())) {
			throw new Exception("Employee name is required");
		}
		
		List<EmployeeModel> empList=new ArrayList<EmployeeModel>();
		empList=employeeRepo.getEmpListByName(name.toLowerCase());
		if(empList.isEmpty()) {
			throw new Exception("There is no employee with these name");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(empList);
		}
	}


	//Method for Update Employee By Id
	public ResponseEntity<EmployeeModel> updateEmployeeById(EmployeeUpdateDAO updateEmployee) throws Exception {
		
		 if(updateEmployee==null || updateEmployee.getId()==null) {
			 throw new Exception("Employee id is Required for update Employee Details");
		 }
		 EmployeeModel empDetails=employeeRepo.findById(updateEmployee.getId()).orElse(null);
		 if(empDetails==null) {
			 throw new Exception("Their is not employee registered with this Employee id:"+updateEmployee.getId());
		 }
		 int i=0;
		 
		 //check for name null value
		 if(updateEmployee.getName()!=null && !updateEmployee.getName().trim().isEmpty()){
			 //check for new name and previous one is not same
			 if(!empDetails.getName().trim().toLowerCase().equals(updateEmployee.getName().trim().toLowerCase())) {
				 empDetails.setName(updateEmployee.getName().trim());
				 i++;
			 }
		 }
		 
		 LocalDate todayDate=LocalDate.now();
		 
		 //Check of age not null value
		 if(updateEmployee.getEmpDOB() != null) {
			 
			 //check for updated date is belongs to our criteria or not
			 
				Period empAge=Period.between(updateEmployee.getEmpDOB(), todayDate);
				
				if(empAge.getYears()<18 || empAge.getYears()>=80) {
					throw new Exception("Required Employee minimum age is 18 and maximum age is 80");
				}			 
			 
			 //check for new age and existing age is same or not
			if(!updateEmployee.getEmpDOB().isEqual(updateEmployee.getEmpDOB())) {
				
			 empDetails.setDob(updateEmployee.getEmpDOB());
				 i++;
			 }
		 }
		 
		 //Check for Employee Date of Joining update
		 //Checking for null value
		 if(updateEmployee.getDateOfJoining()!=null) {
			 
			 if(updateEmployee.getDateOfJoining().isAfter(todayDate)) {
				 throw new Exception("Joining Date is not more that today date");
			 }
			 if(!updateEmployee.getDateOfJoining().isEqual(empDetails.getDateOfJoining())) {
				 
				 empDetails.setDateOfJoining(updateEmployee.getDateOfJoining());
				 i++;
			 }
		 }
		 
		 if(i>0) {
			 empDetails=employeeRepo.save(empDetails);
			 if(empDetails==null) {
				throw new Exception("Something went wrong");
				
			 }else {
				return ResponseEntity.status(HttpStatus.OK).body(empDetails);
			 }
		 }else {
			 
			 throw new Exception("Their is not field to update all are same");
		 }
	}


	
	
	//Method for delete Employee By id
	public ResponseEntity<String> deleteEmployeeById(Long id) throws Exception {
		
		if(id==null) {
			throw new Exception("Employee id is required for deleting the employee ");
		}
		
		boolean isAvailable=employeeRepo.existsById(id);
		if(!isAvailable) {
			throw new Exception("There is not employee registered with this id:"+id);
		}else {
			employeeRepo.deleteById(id);
			boolean confirmationOfDetelion=employeeRepo.existsById(id);
			if(confirmationOfDetelion) {
				throw new Exception("Something went wrong");
			}else {
				return ResponseEntity.status(HttpStatus.OK).body("Employee has been deleted successfully");
			}
		}
	}
}

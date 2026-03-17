package com.employeeRegistration.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employeeRegistration.DAO.EmployeeRegistrationDAO;
import com.employeeRegistration.DAO.EmployeeUpdateDAO;
import com.employeeRegistration.Model.EmployeeModel;
import com.employeeRegistration.Services.EmployeeRegistrationService;





@RestController
@RequestMapping("api/employee")
public class EmployeeController {

	private final EmployeeRegistrationService employeeService;
	
	
	public EmployeeController(EmployeeRegistrationService employeeService) {
		super();
		this.employeeService = employeeService;
	}

	
	//Controller for employee Registration
	@PostMapping("/add")
	public ResponseEntity<EmployeeModel> addEmployee(@RequestBody EmployeeRegistrationDAO employeeDetails) throws Exception {
		
		return employeeService.addEmployee(employeeDetails);
		
	}
	
	
	//Controller for get the list of employee
	@GetMapping("/findAll")
	public ResponseEntity<List<EmployeeModel>> getAllEmployee() throws Exception{
			
	
			
	return employeeService.getAllEmployee();
			
	}
	
	
	//Controller for get Employee by ID
	@GetMapping("/getById/{id}")
	public ResponseEntity<EmployeeModel> getEmployeeById(@PathVariable Long id) throws Exception{
		
		//Integer i= Integer.valueOf(id);
		
		return employeeService.getEmployeeById(id);
		
	}
	
	
	//controller for get employee by name
	@GetMapping("/getByName/{name}")
	public ResponseEntity<List<EmployeeModel>> getEmployeeByName(@PathVariable String name) throws Exception {
		
		return employeeService.getEmployeeByName(name);
		
	}
	
	
	//Controller for update employee details by id 
	@PutMapping("/updateById")
	public ResponseEntity<EmployeeModel> updateEmployeeById(@RequestBody EmployeeUpdateDAO updateEmployee) throws Exception {
		
		return employeeService.updateEmployeeById(updateEmployee);
		
	}
	
	
	//controller for delete employee by id
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) throws Exception{
		
		return employeeService.deleteEmployeeById(id);
		
	}
}

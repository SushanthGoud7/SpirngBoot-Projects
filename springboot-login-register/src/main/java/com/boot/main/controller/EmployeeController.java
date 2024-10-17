package com.boot.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.boot.main.model.Employee;
import com.boot.main.service.EmployeeService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class EmployeeController {
	 
	@Autowired
	private EmployeeService service;
	
	//Register and Login controller
	@PostMapping(path="/register" ,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Employee registerEmployee(@RequestBody Employee employee) throws Exception
	{
		String email= employee.getEmailId();
		if(email !=null && !"".equals(email))
		{
		Employee employeeObj= service.fetchEmployeeByEmailId(email);
		if(employeeObj !=null)
		{
			throw new Exception("Employee with" +email+ " is already exist");
		}
	}
		
		return service.saveEmployee(employee);
		
	}
	
	@PostMapping(path="/login" ,produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Employee loginEmployee(@RequestBody Employee employee) throws Exception
	{
		String email=employee.getEmailId();
		String password=employee.getPassword();
		Employee employeeObj=null;
		if(email!= null && password!=null )
		{
		   employeeObj	=service.fetchEmployeeByEmailIdAndPassword(email, password);
		}
		if(employeeObj==null)
		{
			throw new Exception("Bad Credentials!");
		}
		return employeeObj;
		
	}
	
	@GetMapping (path="/view-employees", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> fetchAllEmployees()
	{
		List<Employee> getEmployees = service.viewAllEmployees();
		HttpHeaders headers=new HttpHeaders();
		headers.add("status","data retrieved successfully!");
		return new ResponseEntity<List<Employee>>(getEmployees, HttpStatus.OK);
	}
	
	
	@GetMapping (path="/get-employee/{id}")
	public ResponseEntity<Employee> getEmployeeByID(@PathVariable("id") int id)
	{
		Employee getById = service.findEmployeeById(id);
		return new ResponseEntity<Employee>(getById,HttpStatus.OK);
	}
	
	@ApiOperation("insertion of employee details")
	@PostMapping(path="/add-employee",produces = MediaType.APPLICATION_JSON_VALUE, consumes =MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <Employee> insertEmployee( @RequestBody Employee employee)
	{
		Employee addEmployee = service.insertEmployee(employee);
		HttpHeaders headers = new HttpHeaders();
		headers.add("status","data added successfully!");
		return new ResponseEntity<Employee>(addEmployee,HttpStatus.CREATED);
	}
	
	@PutMapping(path="/update-employee",produces= MediaType.APPLICATION_JSON_VALUE, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity <Employee> updateEmploye(@RequestBody Employee employee)
	{
		Employee editEmployee= service.updateEmployee(employee);
		HttpHeaders headers = new HttpHeaders();
		headers.add("status","data updated successfully!");
		return new ResponseEntity<Employee>(editEmployee, HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping(path="/delete-employee/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id)
	{
		  service.deleteEmployeeById(id);
		HttpHeaders headers = new HttpHeaders();
		headers.add("status","data deleted successfully!");
		return new ResponseEntity<String>(headers,HttpStatus.OK); 
	}
}
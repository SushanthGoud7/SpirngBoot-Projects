package com.boot.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boot.main.model.Employee;
import com.boot.main.repository.EmployeeRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository repo;
	
	public Employee saveEmployee(Employee employee)
	{
		return repo.save(employee);
	}
	public Employee fetchEmployeeByEmailId(String emailId)
	{
		 return repo.findByEmailId(emailId);
		
	}
	
	public Employee fetchEmployeeByEmailIdAndPassword(String emailId, String password)
	{
		 return repo.findByEmailIdAndPassword(emailId,password);
		
	}
	
	//CRUD implementation..
	
	public List<Employee> viewAllEmployees()
	{
		return repo.findAll();
	}
	
	public Employee findEmployeeById(int id)
    {
        return repo.findById(id);
    }
	
	public Employee insertEmployee(Employee employee)
	{
		return repo.save(employee);
	}
	
	public Employee updateEmployee(Employee employee)
	{
		return repo.save(employee);
	}
	
	public String deleteEmployeeById(int id)
	{
		repo.deleteById(id);
		return "employee deleted!";
	}

}
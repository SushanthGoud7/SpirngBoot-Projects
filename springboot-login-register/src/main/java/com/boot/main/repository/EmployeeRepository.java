package com.boot.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.boot.main.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	//Login and Register...
	Employee findByEmailId(String emailId);
	Employee findByEmailIdAndPassword(String emailId, String password);
	Employee findById(int id);
	
	
	/*@Query(value = "select * from Employee where emailId=: emailId AND password= :password")
	Employee myQuery(String emailId, String password);
	
	@Query(value = "select * from Employee where emailId=: myEmail AND password= :password and salary>=5000 ", nativeQuery = true)
	Employee myQueryTest(@Param ("myEmail") String myEmail, String password);*/
	
	
}
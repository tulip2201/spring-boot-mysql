package com.springboot.mysql.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.mysql.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	@Query("select e from Employee e where e.employeeDepartment =:department and e.employeeName =:name")
	List<Employee> findByEmployeeDepartmentAndEmployeeName(@Param("department") String department,
			@Param("name") String name);
	
	
	List<Employee> findByEmployeeDepartment( String department);
}

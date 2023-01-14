package com.springboot.mysql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.mysql.entity.Employee;
import com.springboot.mysql.repository.EmployeeRepository;

@Service
public class EmployeeService {

	//@Autowired
	//private EmployeeRepository employeeRepository;
	private final EmployeeRepository employeeRepository;
	
	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Employee addEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee findEmployeeById(long employeeId) {
		return employeeRepository.findById(employeeId).orElseThrow(()-> new RuntimeException("Cannot find employee with id : "+employeeId));
	}
	
	public Employee updateEmployeeDetails(Employee employee , long employeeId) {
		Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(()-> new RuntimeException("Cannot find employee with id : "+employeeId));
		
		existingEmployee.setEmployeeName(employee.getEmployeeName());
		existingEmployee.setEmployeeDepartment(employee.getEmployeeDepartment());
		
		return employeeRepository.save(existingEmployee);
	}
	
	public void deleteEmployee(long employeeId) {
		//Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(()-> new RuntimeException("Cannot find employee with id : "+employeeId));
		
		if(employeeRepository.findById(employeeId).isPresent()) {
			employeeRepository.deleteById(employeeId);
		}		
	}
	
	public List<Employee> findByEmployeeDepartmentAndEmployeeName(String employeeDepartment,String employeeName){
		return employeeRepository.findByEmployeeDepartmentAndEmployeeName(employeeDepartment,employeeName);
	}
	
	public List<Employee> findByDepartment(String department){
		return employeeRepository.findByEmployeeDepartment(department);
	}
	
	public List<Employee> findAllEmployees(){
		return employeeRepository.findAll();
	}
}

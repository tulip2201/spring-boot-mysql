package com.springboot.mysql.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.mysql.entity.Employee;
import com.springboot.mysql.service.EmployeeService;

import lombok.Delegate;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
		employeeService.addEmployee(employee);
		return ResponseEntity.
				status(HttpStatus.CREATED).
				build();
	}
	@GetMapping("/employee/{id}")
	public ResponseEntity<Employee> findEmployeeById(@PathVariable("id") Long employeeId){
		Employee employeeById = employeeService.findEmployeeById(employeeId);
		return new ResponseEntity<>(employeeById,HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee,@PathVariable("id") Long employeeId) {
		Employee updatedEmployee = employeeService.updateEmployeeDetails(employee,employeeId);
		return ResponseEntity.ok(updatedEmployee).
				status(HttpStatus.CREATED).
				build();
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Employee> deleteEmployeeById(@PathVariable("id") Long employeeId){
		employeeService.deleteEmployee(employeeId);
		return ResponseEntity.noContent().build();
		
	}
	
	@GetMapping("/{department}/{name}")
	public ResponseEntity<List<Employee>> findEmployeeByDepartmentAndName(@PathVariable("department") String employeeDepartment,@PathVariable("name") String employeeName){
		List<Employee> employeeByDeptAndName = employeeService.findByEmployeeDepartmentAndEmployeeName(employeeDepartment, employeeName);
		return new ResponseEntity<List<Employee>>(employeeByDeptAndName, HttpStatus.OK);
	}
	
	@GetMapping("/{department}")
	public ResponseEntity<List<Employee>> findByDepartment(@PathVariable("department") String employeeDepartment){
		List<Employee> employeeByDept = employeeService.findByDepartment(employeeDepartment);
		return new ResponseEntity<List<Employee>>(employeeByDept, HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> findAllEmployees(){
		List<Employee> allEmployees = employeeService.findAllEmployees();
		return new ResponseEntity<List<Employee>>(allEmployees, HttpStatus.OK);
	}
}

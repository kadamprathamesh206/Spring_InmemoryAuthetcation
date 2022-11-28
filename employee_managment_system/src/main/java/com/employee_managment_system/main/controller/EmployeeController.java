package com.employee_managment_system.main.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee_managment_system.main.model.Employee;
import com.employee_managment_system.main.serviceImpl.EmployeeServiceImpl;
import com.employee_managment_system.main.util.ApiResponse;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeServiceImpl employeeService;
	
	/*
	 * register controller is used for register a new employee 
	 */
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody Employee employee){
		 Employee existing_employee=this.employeeService.findEmployeeByEmail(employee.getEmail());
		 if(existing_employee!=null) {
			 return new ResponseEntity<>(new ApiResponse("Email id alerady used"),HttpStatus.BAD_REQUEST);
		 }
		  return new ResponseEntity<>(new ApiResponse("Employee Register successfully",this.employeeService.addEmployee(employee)),HttpStatus.CREATED );
		
	}
	
	
	@GetMapping("/getAllEmployee")
	public ResponseEntity<?> getAllEmployee(){
	List<Employee> employeeList=this.employeeService.getAllEmployeeList();
     return new ResponseEntity<>(new ApiResponse("Employee List display successfully",employeeList),HttpStatus.OK);
	}
	
	
	@PutMapping("/updateEmployee")
	public ResponseEntity<?> updateEmployee(@RequestBody Employee employee){
		   Employee existing_employee=this.employeeService.getEmployeeById(employee.getId());
		   if(existing_employee==null) {
			   return new ResponseEntity<>(new ApiResponse("Employee not present"),HttpStatus.BAD_REQUEST);
		   }
		  return new ResponseEntity<>(new ApiResponse("Employee Updated successfully",this.employeeService.updateEmployee(employee)),HttpStatus.OK);
	}
	
	
	@DeleteMapping("/deleteEmployee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable("id") int id){
		  Employee existing_employee=this.employeeService.getEmployeeById(id);
		   if(existing_employee==null) {
			   return new ResponseEntity<>(new ApiResponse("Employee not present"),HttpStatus.BAD_REQUEST);
		   }
		   this.employeeService.deleteEmployee(id);
		 return new ResponseEntity<>(new ApiResponse("Employee deleted successfully"),HttpStatus.OK);
	}
	

}

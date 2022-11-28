package com.employee_managment_system.main.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.employee_managment_system.main.Repo.EmployeeRepo;
import com.employee_managment_system.main.model.Employee;
import com.employee_managment_system.main.service.EmployeeService;



@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepo employeeRepo;

	/*
	 * addEmployee method is used for add new employee
	 * 	
	 */
	public Employee addEmployee(Employee employee) {
		Employee saved_employee=	this.employeeRepo.save(employee);
		return employee;

	}

	/*
	 * findEmployeeByEmail  method is used fetching existing employee from database
	 */
	public Employee findEmployeeByEmail(String email) {
		Employee db_employee = this.employeeRepo.findByEmail(email);
		return db_employee;

	}
	/*
	 * getAllEmployeelist method is used for fetching all the employee from the database
	 * 
	 */

	public List<Employee> getAllEmployeeList(){
		List<Employee> employeeList=this.employeeRepo.findAll();
		return employeeList;

	}


	public Employee getEmployeeById(int id) {
		Employee employee=this.employeeRepo.findById(id).orElseThrow();
		return employee;
	}


	public Employee updateEmployee(Employee employee) {
		Employee existing_employee = this.getEmployeeById(employee.getId());
		existing_employee.setAddress(employee.getAddress());
		existing_employee.setEmail(employee.getEmail());
		existing_employee.setJob(employee.getJob());
		existing_employee.setName(employee.getName());
		existing_employee.setPassword(employee.getPassword());
		Employee updated_employee=this.employeeRepo.save(existing_employee);
		return updated_employee;
	}


	public void deleteEmployee(int id) {
		Employee  db_Employee= this.getEmployeeById(id);
		this.employeeRepo.delete(db_Employee);
	}
}

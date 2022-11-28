package com.employee_managment_system.main.service;

import java.util.List;

import com.employee_managment_system.main.model.Employee;

public interface EmployeeService {
	public Employee addEmployee(Employee employee);
	public Employee findEmployeeByEmail(String email);
	public List<Employee> getAllEmployeeList();
	public Employee getEmployeeById(int id);
	public Employee updateEmployee(Employee employee);
	public void deleteEmployee(int id);
}

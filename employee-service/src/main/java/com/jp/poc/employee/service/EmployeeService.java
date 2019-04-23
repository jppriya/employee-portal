package com.jp.poc.employee.service;

import java.util.List;

import com.jp.poc.employee.model.EmployeeDTO;

public interface EmployeeService {

	List<EmployeeDTO> getAllEmployee();
	
	EmployeeDTO addEmployee(EmployeeDTO employeeDTO);

}

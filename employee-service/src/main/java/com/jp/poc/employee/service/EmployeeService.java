package com.jp.poc.employee.service;

import java.util.List;

import com.jp.poc.employee.model.EmployeeDTO;
import com.jp.poc.employee.model.EmployeeWrapper;

public interface EmployeeService {

	List<EmployeeDTO> getAllEmployee();
	
	EmployeeDTO addEmployee(EmployeeDTO employeeDTO);

	List<EmployeeDTO> uploadEmployee(EmployeeWrapper employeeWrapper);

	EmployeeDTO getEmployee(String id);

	EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);

}

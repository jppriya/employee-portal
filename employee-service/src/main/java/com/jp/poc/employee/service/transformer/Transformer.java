package com.jp.poc.employee.service.transformer;

import java.util.List;

import com.jp.poc.employee.entity.Employee;
import com.jp.poc.employee.model.EmployeeDTO;

public interface Transformer {

	List<EmployeeDTO> transform(List<Employee> employee);
	
	List<Employee> transformTOEmployee(List<EmployeeDTO> employeeDTO);

	EmployeeDTO transform(Employee employee);

	Employee transform(EmployeeDTO employeeDTO);
	
	Employee transform(EmployeeDTO source, Employee destination);
}

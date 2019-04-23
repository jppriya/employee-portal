package com.jp.poc.employee.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.poc.employee.entity.Employee;
import com.jp.poc.employee.model.EmployeeDTO;
import com.jp.poc.employee.repository.EmployeeRepository;
import com.jp.poc.employee.service.transformer.EmployeeTransformer;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmployeeTransformer employeeTransformer;

	@Override
	public List<EmployeeDTO> getAllEmployee() {
		List<Employee> allEmployees = employeeRepository.findAll();
		return employeeTransformer.transform(allEmployees);
	}

	@Override
	public EmployeeDTO addEmployee(EmployeeDTO employeeDTO) {
		Employee employee = employeeTransformer.transform(employeeDTO);
		Employee savedEmployee = employeeRepository.save(employee);
		return employeeTransformer.transform(savedEmployee);
	}

}

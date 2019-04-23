package com.jp.poc.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jp.poc.employee.model.EmployeeDTO;
import com.jp.poc.employee.service.EmployeeService;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping(value="/employee", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<EmployeeDTO> getAllEmployees() {
		return employeeService.getAllEmployee();
	}
	
	@PostMapping(value="/employee", produces=MediaType.APPLICATION_JSON_VALUE)
	public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO) {
		return employeeService.addEmployee(employeeDTO);
	}

}

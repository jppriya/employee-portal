package com.jp.poc.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jp.poc.employee.model.EmployeeDTO;
import com.jp.poc.employee.model.EmployeeWrapper;
import com.jp.poc.employee.service.EmployeeService;

@RestController
@CrossOrigin(origins = "*")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<EmployeeDTO> getAllEmployees() {
		return employeeService.getAllEmployee();
	}

	@GetMapping(value = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeDTO getAllEmployees(@PathVariable("id") String id) {
		return employeeService.getEmployee(id);
	}

	@PostMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_VALUE)
	public EmployeeDTO addEmployee(@RequestBody EmployeeDTO employeeDTO) {
		return employeeService.addEmployee(employeeDTO);
	}

	@PostMapping("/upload")
	public List<EmployeeDTO> uploadEmployee(@RequestBody EmployeeWrapper employeeWrapper) {
		return employeeService.uploadEmployee(employeeWrapper);
	}

	@PutMapping("/employee/{id}")
	public EmployeeDTO updateEmployee(@PathVariable("id") Long id, @RequestBody EmployeeDTO employeeDTO) {
		return employeeService.updateEmployee(id, employeeDTO);
	}
}

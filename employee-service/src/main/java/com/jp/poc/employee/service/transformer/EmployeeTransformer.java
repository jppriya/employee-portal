package com.jp.poc.employee.service.transformer;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import com.jp.poc.employee.entity.Employee;
import com.jp.poc.employee.model.EmployeeDTO;

@Component
public class EmployeeTransformer implements Transformer {

	@Override
	public List<EmployeeDTO> transform(List<Employee> employee) {
		if (CollectionUtils.isEmpty(employee)) {
			return null;
		}
		List<EmployeeDTO> employeeDTOs = employee.stream().map(emp -> transform(emp)).collect(Collectors.toList());
		return employeeDTOs;
	}

	@Override
	public List<Employee> transformTOEmployee(List<EmployeeDTO> employeeDTO) {
		if (CollectionUtils.isEmpty(employeeDTO)) {
			return null;
		}
		List<Employee> employee = employeeDTO.stream().map(emp -> transform(emp)).collect(Collectors.toList());
		return employee;
	}

	@Override
	public EmployeeDTO transform(Employee employee) {
		if (null == employee) {
			return null;
		}
		EmployeeDTO employeeDTO = new EmployeeDTO();
		employeeDTO.setFirstName(employee.getFirstName());
		employeeDTO.setLastName(employee.getLastName());
		employeeDTO.setGender(employee.getGender());
		employeeDTO.setDob(employee.getDob());
		employeeDTO.setEmployeeId(employee.getEmployeeId());
		return employeeDTO;
	}

	@Override
	public Employee transform(EmployeeDTO employeeDTO) {
		if (null == employeeDTO) {
			return null;
		}
		Employee employee = new Employee();
		employee.setDepartment(employeeDTO.getDepartment());
		employee.setDob(employeeDTO.getDob());
		employee.setFirstName(employeeDTO.getFirstName());
		employee.setLastName(employeeDTO.getLastName());
		employee.setGender(employeeDTO.getGender());
		return employee;
	}

	@Override
	public Employee transform(EmployeeDTO source, Employee destination) {
		if (destination == null) {
			destination = new Employee();
		}
		if (source == null) {
			return destination;
		}
		destination.setEmployeeId(source.getEmployeeId());
		destination.setFirstName(source.getFirstName());
		destination.setLastName(source.getLastName());
		destination.setGender(source.getGender());
		destination.setDob(source.getDob());
		return destination;
	}

}

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
		if(CollectionUtils.isEmpty(employee)){
			return null;
		}
		List<EmployeeDTO> employeeDTOs = employee.stream().map(emp -> transform(emp)).collect(Collectors.toList());
		return employeeDTOs;
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

}

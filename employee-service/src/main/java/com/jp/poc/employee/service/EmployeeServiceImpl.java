package com.jp.poc.employee.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jp.poc.employee.entity.Employee;
import com.jp.poc.employee.model.EmployeeDTO;
import com.jp.poc.employee.model.EmployeeWrapper;
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

	@Override
	public List<EmployeeDTO> uploadEmployee(EmployeeWrapper employeeWrapper) {
		byte[] byteArray = Base64.getDecoder().decode(employeeWrapper.getFileBody());
		InputStream inputStream = new ByteArrayInputStream(byteArray);
		List<EmployeeDTO> employeeDetails = getEmployeeDetails(inputStream, employeeWrapper);
		List<Employee> employees = employeeTransformer.transformTOEmployee(employeeDetails);
		List<Employee> savedEmployees = employeeRepository.saveAll(employees);
		return employeeTransformer.transform(savedEmployees);
	}

	private List<EmployeeDTO> getEmployeeDetails(InputStream inputStream, EmployeeWrapper empWrapper) {
		XSSFWorkbook workbook = null;
		List<EmployeeDTO> employees = new ArrayList<>();
		try {
			if (null == workbook) {
				workbook = new XSSFWorkbook(inputStream);
			}
			XSSFSheet sheet = workbook.getSheetAt(0);
			if (null != workbook) {
				employees = readCancellationRequests(sheet, employees, empWrapper);
				workbook.close();
			}
		} catch (IOException e) {
		}
		return employees;
	}

	private List<EmployeeDTO> readCancellationRequests(XSSFSheet sheet, List<EmployeeDTO> employees,
			EmployeeWrapper empWrapper) {
		int dataRows = sheet.getPhysicalNumberOfRows();
		for (int rowNum = 1; rowNum < dataRows; rowNum++) {
			Row dataColumn = sheet.getRow(rowNum);
			EmployeeDTO employee = new EmployeeDTO();
			employee.setEmployeeId(Long.valueOf(String.valueOf(dataColumn.getCell(0)).split("\\.")[0]));
			employee.setFirstName(String.valueOf(dataColumn.getCell(1)));
			employee.setLastName(String.valueOf(dataColumn.getCell(2)));
			employee.setGender(String.valueOf(dataColumn.getCell(3)));
			employee.setDob(new Date(String.valueOf(dataColumn.getCell(4))));
			employees.add(employee);
		}
		return employees;
	}

	@Override
	public EmployeeDTO getEmployee(String id) {
		Optional<Employee> employee = employeeRepository.findById(Long.valueOf(id));
		if (employee.isPresent()) {
			EmployeeDTO employeeDTO = employeeTransformer.transform(employee.get());
			return employeeDTO;
		}
		return null;
	}

	@Override
	public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
		Optional<Employee> employee = employeeRepository.findById(Long.valueOf(id));
		if (employee.isPresent()) {
			Employee emp = employeeTransformer.transform(employeeDTO, employee.get());
			Employee savedEmp = employeeRepository.save(emp);
			return employeeTransformer.transform(savedEmp);
		}
		return null;
	}

}

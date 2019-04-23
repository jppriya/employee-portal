package com.jp.poc.employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jp.poc.employee.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

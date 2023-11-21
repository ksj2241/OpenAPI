package com.multi.contactsapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.multi.contactsapp.domain.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
	List<Employee> findByEmpNameStartingWith(String empName);
}

package com.multi.contactsapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multi.contactsapp.dao.EmployeeRepository;
import com.multi.contactsapp.domain.Employee;

@RestController
@RequestMapping(value = "emps")
public class EmployeeController {
	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("find1/{name}")
	public List<Employee> getDepartment1(@PathVariable("name") String name) {
		return employeeRepository.findByEmpNameStartingWith(name);
	}
}
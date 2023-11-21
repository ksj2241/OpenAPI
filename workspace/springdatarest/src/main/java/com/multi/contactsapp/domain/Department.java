package com.multi.contactsapp.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Department {
	@Id
	@Column(name="DEPT_ID")
	 private String id;
	 private String deptName;
	 private String location;
	 
	public Department() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Department(String id, String deptName, String location) {
		super();
		this.id = id;
		this.deptName = deptName;
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	 
	 
}

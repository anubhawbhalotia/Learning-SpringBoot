package io.demoproject.employee;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

import org.springframework.validation.annotation.Validated;

public class EmployeeCreateRequest {
	private String id;
	@NotBlank
	private String name;
	@NotBlank
	private String email;
	private String phone;
	private String designation;
	
	public EmployeeCreateRequest() {
		
	}
	
	public EmployeeCreateRequest(String id, String name, String email, String phone, String designation) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.designation = designation;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getDesignation() {
		return designation;
	}
	
	public Employee toEmployee() {
		return new Employee(
				getId(),
				getName(),
				getEmail(),
				getPhone(),
				getDesignation()
			);
				
	}
	
}

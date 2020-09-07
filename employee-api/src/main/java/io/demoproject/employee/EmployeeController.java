package io.demoproject.employee;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {
	
	private EmployeeCreateRequestValidator employeeCreateRequestValidator;
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeCreateRequestValidator employeeCreateRequestValidator, 
			EmployeeRepository employeeRepository) {
	    this.employeeCreateRequestValidator = employeeCreateRequestValidator;
	    this.employeeRepository = employeeRepository;
	}

	@InitBinder("employeeCreateRequest")
	public void setupBinder(WebDataBinder binder) {
	    binder.addValidators(employeeCreateRequestValidator);
	}
	
	
	
	@RequestMapping("/employees")
	public List<Employee> getAllEmployees() {
		return employeeService.getAllEmployees();
	}
	
	@RequestMapping("/employees/{id}")
	public Optional<Employee> getEmployee(@PathVariable String id) {
		return employeeService.getEmployee(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/employees")
	public Employee addEmployee(@Valid @RequestBody EmployeeCreateRequest employeeCreateRequest) {
		Employee employee = employeeCreateRequest.toEmployee();
		
		employeeService.addEmployee(employee);
		
		return employee;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/employees/{id}")
	public void updateEmployee(@RequestBody Employee employee, @PathVariable String id) {
		employeeService.updateEmployee(id, employee);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/employees/{id}")
	public void deleteEmployee(@PathVariable String id) {
		employeeService.deleteEmployee(id);
	}
	
} 

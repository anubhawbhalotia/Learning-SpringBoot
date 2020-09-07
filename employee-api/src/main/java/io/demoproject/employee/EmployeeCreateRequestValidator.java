package io.demoproject.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class EmployeeCreateRequestValidator implements Validator {
    private EmployeeRepository EmployeeRepository;

    @Autowired
    public EmployeeCreateRequestValidator(EmployeeRepository EmployeeRepository) {
        this.EmployeeRepository = EmployeeRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return EmployeeCreateRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	EmployeeCreateRequest EmployeeCreateRequest = (EmployeeCreateRequest) target;

        if (EmployeeRepository.existsById(EmployeeCreateRequest.toEmployee().getId())) {
            errors.reject("ALREADY_EXISTS");
        }
       
    }
}
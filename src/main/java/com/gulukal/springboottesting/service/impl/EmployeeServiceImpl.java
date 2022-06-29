package com.gulukal.springboottesting.service.impl;

import com.gulukal.springboottesting.exception.ResourceNorFoundException;
import com.gulukal.springboottesting.model.Employee;
import com.gulukal.springboottesting.repository.EmployeeRepository;
import com.gulukal.springboottesting.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    //construction injection
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {

        Optional<Employee> savedEmployee = employeeRepository.findByEmail(employee.getEmail());
        if(savedEmployee.isPresent()){
            throw new ResourceNorFoundException("Employee already exist with given email:" + employee.getEmail());
        }
        return employeeRepository.save(employee);
    }
}

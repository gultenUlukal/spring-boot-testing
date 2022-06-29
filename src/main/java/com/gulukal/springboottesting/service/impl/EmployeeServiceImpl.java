package com.gulukal.springboottesting.service.impl;

import com.gulukal.springboottesting.model.Employee;
import com.gulukal.springboottesting.repository.EmployeeRepository;
import com.gulukal.springboottesting.service.EmployeeService;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    //construction injection
    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
}

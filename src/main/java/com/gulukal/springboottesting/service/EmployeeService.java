package com.gulukal.springboottesting.service;

import com.gulukal.springboottesting.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee saveEmployee(Employee employee);
    List<Employee> getAllEmployees();
}

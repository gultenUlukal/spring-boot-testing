package com.gulukal.springboottesting.repository;

import com.gulukal.springboottesting.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    //custom query
    Optional<Employee> findByEmail(String email);
}

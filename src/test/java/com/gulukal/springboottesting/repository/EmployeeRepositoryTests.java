package com.gulukal.springboottesting.repository;


import com.gulukal.springboottesting.model.Employee;
//create static import -->
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

//transaction and rollback
@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    //JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Gulten")
                .lastName("Ulukal")
                .email("gulten.ulukal@gmail.com")
                .build();

        //when - action or behavior we are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        //then - verify the output (Assertj)
        // --> Assertions is a static method so we need to called it by class if we can change import as a static we will not need to write Assertions
        // Assertions.assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee).isNotNull();
        // Assertions.assertThat(savedEmployee.getId()).isGreaterThan(0);
        assertThat(savedEmployee.getId()).isGreaterThan(0);

    }

    //JUnit test for findAll employee operation
    @DisplayName("JUnit test for findAll employee operation")
    @Test
    public void givenEmployeeList_whenFindAll_thenReturnSavedEmployeeList(){

        //given - precondition or setup
        Employee employee = Employee.builder()
                .firstName("Gulten")
                .lastName("Ulukal")
                .email("gulten.ulukal@gmail.com")
                .build();

        Employee secondEmployee = Employee.builder()
                .firstName("Deniz")
                .lastName("Yonkuc")
                .email("deniz.yonkuc@gmail.com")
                .build();

        employeeRepository.save(employee);
        employeeRepository.save(secondEmployee);

        //when - action or behavior we are going to test
        List<Employee>employeeList =employeeRepository.findAll();

        //then - verify the output (Assertj)
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }
}

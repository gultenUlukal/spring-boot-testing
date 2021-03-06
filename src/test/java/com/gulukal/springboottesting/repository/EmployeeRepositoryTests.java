package com.gulukal.springboottesting.repository;


import com.gulukal.springboottesting.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//transaction and rollback
@DataJpaTest
public class EmployeeRepositoryTests {

    @Autowired
    private EmployeeRepository employeeRepository;

    private Employee employee;
    private Employee savedEmployee;
    private final String firstName = "Gulten";
    private final String lastName = "Ulukal";
    @BeforeEach
    public void setup(){
        employee = Employee.builder()
                .firstName("Gulten")
                .lastName("Ulukal")
                .email("gulten.ulukal@gmail.com")
                .build();

        savedEmployee = employeeRepository.save(employee);

    }

    //JUnit test for save employee operation
    @DisplayName("JUnit test for save employee operation")
    @Test
    public void givenEmployeeObject_whenSave_thenReturnSavedEmployee(){

        //given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Gulten")
//                .lastName("Ulukal")
//                .email("gulten.ulukal@gmail.com")
//                .build();

        //when - action or behavior we are going to test
//        Employee savedEmployee = employeeRepository.save(employee);

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
//        Employee employee = Employee.builder()
//                .firstName("Gulten")
//                .lastName("Ulukal")
//                .email("gulten.ulukal@gmail.com")
//                .build();

        Employee secondEmployee = Employee.builder()
                .firstName("Deniz")
                .lastName("Yonkuc")
                .email("deniz.yonkuc@gmail.com")
                .build();

//        employeeRepository.save(employee);
        employeeRepository.save(secondEmployee);

        //when - action or behavior we are going to test
        List<Employee>employeeList =employeeRepository.findAll();

        //then - verify the output (Assertj)
        assertThat(employeeList).isNotNull();
        assertThat(employeeList.size()).isEqualTo(2);

    }


    //JUnit test for get employee by id operation
    @DisplayName("JUnit test for get employee by id operation")
    @Test
    public void givenEmployeeObject_whenFindById_thenReturnEmployeeObject() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Gulten")
//                .lastName("Ulukal")
//                .email("gulten.ulukal@gmail.com")
//                .build();
//        employeeRepository.save(employee);

        // when - action or behavior that are going to test
        // orElseThrow() --> for optional return
        Employee employeeDB = employeeRepository.findById(employee.getId()).orElseThrow();

        // then - verify the output
        assertThat(employeeDB).isNotNull();

    }

    //JUnit test for get employee by email operation
    @DisplayName("JUnit test for get employee by email operation")
    @Test
    public void givenEmployeeEmail_whenFindByEmail_thenEmployeeObject() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Gulten")
//                .lastName("Ulukal")
//                .email("gulten.ulukal@gmail.com")
//                .build();
//        employeeRepository.save(employee);

        // when - action or behavior that are going to test
        // orElseThrow() --> for optional return
        Employee employeeByEmail = employeeRepository.findByEmail(employee.getEmail()).orElseThrow();

        // then - verify the output
        assertThat(employeeByEmail).isNotNull();

    }

    //JUnit test for update employee operation
    @DisplayName("JUnit test for update employee operation ")
    @Test
    public void givenEmployeeObject_whenUpdateEmployee_thenReturnUpdatedEmployee() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Gulten")
//                .lastName("Ulukal")
//                .email("gulten.ulukal@gmail.com")
//                .build();
//        employeeRepository.save(employee);

        // when - action or behavior that are going to test
        // orElseThrow() --> for optional return
        Employee savedEmployee = employeeRepository.findById(employee.getId()).orElseThrow();
        savedEmployee.setEmail("basak.ulukal@gmail.com");
        savedEmployee.setFirstName("Basak");
        Employee updatedEmployee = employeeRepository.save(savedEmployee);

        // then - verify the output
        assertThat(updatedEmployee.getEmail()).isEqualTo("basak.ulukal@gmail.com");
        assertThat(updatedEmployee.getFirstName()).isEqualTo("Basak");

    }

    //JUnit test for delete employee operation
    @DisplayName("JUnit test for delete employee operation")
    @Test
    public void givenEmployeeObject_whenDelete_thenReturnRemovedEmployeeObject() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Gulten")
//                .lastName("Ulukal")
//                .email("gulten.ulukal@gmail.com")
//                .build();
//        employeeRepository.save(employee);

        // when - action or behavior that are going to test
        employeeRepository.delete(employee);
        Optional<Employee> employeeOptional = employeeRepository.findById(employee.getId());

        // then - verify the output
        assertThat(employeeOptional).isEmpty();

    }

    //JUnit test for custom query JPQL with index
    @DisplayName("JUnit test for custom query JPQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedIndex_thenReturnEmployeeObject() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Gulten")
//                .lastName("Ulukal")
//                .email("gulten.ulukal@gmail.com")
//                .build();
//        employeeRepository.save(employee);
//        String firstName = "Gulten";
//        String lastName = "Ulukal";

        // when - action or behavior that are going to test
        Employee savedEmployee = employeeRepository.findByJPQLIndexParams(firstName,lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();

    }

    //JUnit test for custom query JPQL with named param
    @DisplayName("JUnit test for custom query JPQL with named param")
    @Test
    public void givenFirstNameAndLastName_whenFindByJPQLNamedParams_thenReturnEmployeeObject() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Gulten")
//                .lastName("Ulukal")
//                .email("gulten.ulukal@gmail.com")
//                .build();
//        employeeRepository.save(employee);
//        String firstName = "Gulten";
//        String lastName = "Ulukal";

        // when - action or behavior that are going to test
        Employee savedEmployee = employeeRepository.findByJPQLNamedParams(firstName,lastName);

        // then - verify the output
        assertThat(savedEmployee).isNotNull();

    }

    //JUnit test for custom query native SQL with index
    @DisplayName("JUnit test for custom query native SQL with index")
    @Test
    public void givenFirstNameAndLastName_whenFindBySQLIndexParams_thenReturnEmployeeObject() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Gulten")
//                .lastName("Ulukal")
//                .email("gulten.ulukal@gmail.com")
//                .build();
//        employeeRepository.save(employee);
//        String firstName = "Gulten";
//        String lastName = "Ulukal";

        // when - action or behavior that are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQLIndexParams(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();

    }
    //JUnit test for custom query native SQL with named param
    @DisplayName("JUnit test for custom query native SQL with named param")
    @Test
    public void givenFirstNameAndLastName_whenFindBySQLNamedParams_thenReturnEmployeeObject() {
        // given - precondition or setup
//        Employee employee = Employee.builder()
//                .firstName("Gulten")
//                .lastName("Ulukal")
//                .email("gulten.ulukal@gmail.com")
//                .build();
//        employeeRepository.save(employee);
//        String firstName = "Gulten";
//        String lastName = "Ulukal";

        // when - action or behavior that are going to test
        Employee savedEmployee = employeeRepository.findByNativeSQNamedParams(employee.getFirstName(), employee.getLastName());

        // then - verify the output
        assertThat(savedEmployee).isNotNull();

    }

    }

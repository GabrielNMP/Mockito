package pro.sky.java.course2.mockito.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pro.sky.java.course2.mockito.book.Employee;
import pro.sky.java.course2.mockito.exception.EmployeeAlredyInnExceptions;
import pro.sky.java.course2.mockito.exception.EmployeeNotFoudExceptions;
import pro.sky.java.course2.mockito.exception.IllegalNameExceptions;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class EmployeeServiceImplTest {

    private final EmployeeServiceImpl out = new EmployeeServiceImpl();

    @ParameterizedTest
    @MethodSource("params")
    public void correctAddEmployee(String firstName,
                                   String lastName,
                                   int dept,
                                   int salary){
        Employee expected = new Employee(firstName, lastName, dept, salary);
        assertThat(out.addEmployee(firstName,lastName,dept,salary)).isEqualTo(expected);

        assertThatExceptionOfType(EmployeeAlredyInnExceptions.class)
                .isThrownBy(()-> out.addEmployee(firstName,lastName,dept,salary));
    }

    @ParameterizedTest
    @MethodSource ("params")
    public void positiveDeleteEmployee(String firstName,
                                       String lastName,
                                       int dept,
                                       int salary){
        Employee expected = new Employee(firstName,lastName,dept, salary);
        assertThat(out.addEmployee(firstName, lastName, dept, salary)).isEqualTo(expected);

        assertThat(out.deleteEmployee(firstName, lastName, dept, salary)).isEqualTo(expected);
        assertThat(out.allEmployees()).isEmpty();
    }

    @ParameterizedTest
    @MethodSource ("params")
    public void negativeDeleteEmployee(String firstName,
                                       String lastName,
                                       int dept,
                                       int salary){
        assertThatExceptionOfType(EmployeeNotFoudExceptions.class)
                .isThrownBy(()-> out.deleteEmployee("test","test",0, 0));

        Employee expected = new Employee(firstName,lastName,dept, salary);
        assertThat(out.addEmployee(firstName, lastName, dept, salary)).isEqualTo(expected);

        assertThatExceptionOfType(EmployeeNotFoudExceptions.class)
                .isThrownBy(()-> out.deleteEmployee("test","test",0, 0));
    }

    @ParameterizedTest
    @MethodSource ("params")
    public void positivePassEmployee(String firstName,
                                       String lastName,
                                       int dept,
                                       int salary){
        Employee expected = new Employee(firstName,lastName,dept, salary);
        assertThat(out.addEmployee(firstName, lastName, dept, salary)).isEqualTo(expected);

        assertThat(out.passEmployee(firstName, lastName, dept, salary)).isEqualTo(expected);
        assertThat(out.allEmployees()).hasSize(1);
    }

    @ParameterizedTest
    @MethodSource ("params")
    public void negativePassEmployee(String firstName,
                                       String lastName,
                                       int dept,
                                       int salary){
        assertThatExceptionOfType(EmployeeNotFoudExceptions.class)
                .isThrownBy(()-> out.passEmployee("test","test",0, 0));

        Employee expected = new Employee(firstName,lastName,dept, salary);
        assertThat(out.addEmployee(firstName, lastName, dept, salary)).isEqualTo(expected);

        assertThatExceptionOfType(EmployeeNotFoudExceptions.class)
                .isThrownBy(()-> out.passEmployee("test","test",0, 0));
    }

    @Test
    public void invalidNameInput() {

        assertThatExceptionOfType(IllegalNameExceptions.class)
                .isThrownBy(()-> out.validateNameInput("###","lastName"));

        assertThatExceptionOfType(IllegalNameExceptions.class)
                .isThrownBy(()-> out.validateNameInput("firstName","!!"));

        assertThatExceptionOfType(IllegalNameExceptions.class)
                .isThrownBy(()-> out.validateNameInput("###","##"));
    }



    public static Stream<Arguments> params(){
        return Stream.of(
                Arguments.of("Ivan","Ivanov", 1, 60000),
                Arguments.of("Petr","Petrov", 1, 70000),
                Arguments.of("Sidor","Sidorov", 2, 55000),
                Arguments.of("Luka","Lukin", 3, 85000),
                Arguments.of("Maria","Maryina", 2, 75000),
                Arguments.of("Nina","Ninina", 3, 45000)
        );
    }


}
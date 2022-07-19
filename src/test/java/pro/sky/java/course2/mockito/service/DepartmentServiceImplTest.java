package pro.sky.java.course2.mockito.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.course2.mockito.book.Employee;
import pro.sky.java.course2.mockito.exception.EmployeeNotFoudExceptions;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    
    @Mock
    private EmployeeServiceImpl employeeServiceImpl;

    @InjectMocks
    private DepartmentServiceImpl departmentServiceImpl;
    
    @BeforeEach
    public void beforEachTest() {
        List<Employee> employees = List.of(
        new Employee("Ivan", "Ivanov", 1, 50000),
        new Employee("Sidor", "Sidorov", 2, 60000),
        new Employee("Petr", "Petrov", 3, 80000),
        new Employee("Cris", "Ivanova", 1, 60000),
        new Employee("Janna", "Petrova", 3, 50000),
        new Employee("Alexandra", "Sidorova", 2, 90000)
        );
        when(employeeServiceImpl.allEmployees()).thenReturn(employees);
    }

    @ParameterizedTest
    @MethodSource("paramsMaxSalary")
    public void maxSalaryPositiveTest(int dept,
                                      Employee expected){
        assertThat(departmentServiceImpl.maxSalary(dept)).isEqualTo(expected);
    }

    @Test
    public void maxSalaryNegativeTest(){
        assertThatExceptionOfType(EmployeeNotFoudExceptions.class)
                .isThrownBy(()-> departmentServiceImpl.maxSalary(6));
    }

    @ParameterizedTest
    @MethodSource("paramsMinSalary")
    public void minSalaryPositiveTest(int dept,
                                      Employee expected){
        assertThat(departmentServiceImpl.minSalary(dept)).isEqualTo(expected);
    }

    @Test
    public void minSalaryNegativeTest(){
        assertThatExceptionOfType(EmployeeNotFoudExceptions.class)
                .isThrownBy(()-> departmentServiceImpl.minSalary(4));
    }

    @ParameterizedTest
    @MethodSource("paramsEmployeesFromDept")
    public void employeesFromDeptPositive(int dept,
                                      List<Employee> expected){
        assertThat(departmentServiceImpl.employeesFromDepartment(dept)).containsExactlyElementsOf(expected);
    }

    @Test
    public void employeesFromDeptNegative(){
        assertThat(departmentServiceImpl.employeesFromDepartment(7)).isEmpty();
    }

    @Test
    public void allEmployeesByDept(){
        assertThat(departmentServiceImpl.allEmployeesByDepartments()).containsAllEntriesOf(
                Map.of(1,List.of(new Employee( "Ivan", "Ivanov", 1, 50000),new Employee( "Cris", "Ivanova", 1, 60000)),
                        2,List.of(new Employee("Sidor", "Sidorov", 2, 60000),new Employee("Alexandra", "Sidorova", 2, 90000)),
                        3, List.of(new Employee("Petr", "Petrov", 3, 80000), new Employee("Janna", "Petrova", 3, 50000)))
        );

    }

    public static Stream<Arguments> paramsMaxSalary() {
        return Stream.of(
                Arguments.of(1,new Employee( "Cris", "Ivanova", 1, 60000)),
                Arguments.of(2, new Employee("Alexandra", "Sidorova", 2, 90000)),
                Arguments.of(3, new Employee("Petr", "Petrov", 3, 80000))
        );
    }

    public static Stream<Arguments> paramsMinSalary() {
        return Stream.of(
                Arguments.of(1, new Employee( "Ivan", "Ivanov", 1, 50000)),
                Arguments.of(2, new Employee("Sidor", "Sidorov", 2, 60000)),
                Arguments.of(3, new Employee("Janna", "Petrova", 3, 50000))
        );
    }

    public static Stream<Arguments> paramsEmployeesFromDept() {
        return Stream.of(
                Arguments.of(1, List.of(new Employee( "Ivan", "Ivanov", 1, 50000),new Employee( "Cris", "Ivanova", 1, 60000))),
                Arguments.of(2, List.of(new Employee("Sidor", "Sidorov", 2, 60000),new Employee("Alexandra", "Sidorova", 2, 90000))),
                Arguments.of(3, List.of(new Employee("Petr", "Petrov", 3, 80000), new Employee("Janna", "Petrova", 3, 50000)))
        );
    }

}
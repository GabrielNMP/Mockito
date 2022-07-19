package pro.sky.java.course2.mockito.service;

import pro.sky.java.course2.mockito.book.Employee;

import java.util.List;


public interface EmployeeService {
    Employee passEmployee (String firstName, String lastName, int dept, int salary);
    Employee addEmployee (String firstName, String lastName, int dept, int salary);
    Employee deleteEmployee (String firstName, String lastName, int dept, int salary);
    List<Employee> allEmployees();
}

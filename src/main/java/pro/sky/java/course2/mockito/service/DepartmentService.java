package pro.sky.java.course2.mockito.service;

import pro.sky.java.course2.mockito.book.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee maxSalary(int dept);

    Employee minSalary(int dept);

    List<Employee> employeesFromDepartment(int dept);

    Map<Integer, List<Employee>> allEmployeesByDepartments();
}

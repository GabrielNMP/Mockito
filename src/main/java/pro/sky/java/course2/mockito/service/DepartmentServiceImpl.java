package pro.sky.java.course2.mockito.service;

import org.springframework.stereotype.Service;
import pro.sky.java.course2.mockito.book.Employee;
import pro.sky.java.course2.mockito.exception.EmployeeNotFoudExceptions;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final Map<String,Employee> deptMap = new HashMap<>();
    private final EmployeeServiceImpl employeeService;

    public DepartmentServiceImpl(EmployeeServiceImpl employeeService) {
        this.employeeService = employeeService;
    }

    //поиск сотрудника с максимальной зарплатой в отделе
    @Override
    public Employee maxSalary(int dept) {
        return employeeService.allEmployees().stream()
                .filter(p -> p.getDept() == dept)
                .max(Comparator.comparingInt(c -> c.getSalary()))
                .orElseThrow(EmployeeNotFoudExceptions::new);
    }

    //поиск сотрудника с минимальной зарплатой в отделе
    @Override
    public Employee minSalary(int dept) {
        return employeeService.allEmployees().stream()
                .filter(p -> p.getDept() == dept)
                .min(Comparator.comparingInt(c -> c.getSalary()))
                .orElseThrow(EmployeeNotFoudExceptions::new);
    }

    // все сотрудники выбранного департамента
    @Override
    public List<Employee> employeesFromDepartment(int dept) {
        return employeeService.allEmployees().stream()
                .filter(p -> p.getDept() ==dept)
                .collect(Collectors.toList());
    }

    // все сотрудники отсортированные по департаментам
    @Override
    public Map<Integer, List<Employee>> allEmployeesByDepartments() {
        return employeeService.allEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDept));
    }

}

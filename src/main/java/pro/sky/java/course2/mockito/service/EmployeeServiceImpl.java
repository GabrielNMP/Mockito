package pro.sky.java.course2.mockito.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.java.course2.mockito.book.Employee;
import pro.sky.java.course2.mockito.exception.EmployeeAlredyInnExceptions;
import pro.sky.java.course2.mockito.exception.EmployeeNotFoudExceptions;
import pro.sky.java.course2.mockito.exception.IllegalNameExceptions;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employeeMap;

    public EmployeeServiceImpl() {
        this.employeeMap = new HashMap<>();
    }

    private String getKey(Employee employee) {
        return employee.getFirstName() + employee.getLastName();
    }

    //поиск сотрудника
    @Override
    public Employee passEmployee(String firstName, String lastName, int dept, int salary) {
        Employee employee = new Employee(firstName, lastName, dept, salary);
        validateNameInput(firstName, lastName);
        if (employeeMap.containsKey(getKey(employee))) {
            return employee;
        }
        throw new EmployeeNotFoudExceptions();
    }

    // добавление сотрудника
    @Override
    public Employee addEmployee(String firstName, String lastName, int dept, int salary) {
        Employee employee = new Employee(firstName, lastName, dept, salary);
        validateNameInput(firstName, lastName);
        if (employeeMap.containsKey(getKey(employee))) {
            throw new EmployeeAlredyInnExceptions("Такой сотрудник уже есть в списке ");
        }
        employeeMap.put(getKey(employee), employee);
        return employee;
    }

    // удаление сотрудника
    @Override
    public Employee deleteEmployee(String firstName, String lastName, int dept, int salary) {
        Employee employee = new Employee(firstName, lastName, dept, salary);
        validateNameInput(firstName,lastName);
        if (employeeMap.containsKey(getKey(employee))) {
            employeeMap.remove(getKey(employee), employee);
            return employee;
        }
        throw new EmployeeNotFoudExceptions(" Такого сотрудника нет в списке ");
    }

//проверка валидности ввода имени
    public void validateNameInput(String firstName, String lastName){
        if (!StringUtils.isAlpha(firstName) || !StringUtils.isAlpha(lastName)){
        throw new IllegalNameExceptions(" Неверно задано имя сотрудника ");
        }
    }

    //список всех сотрудников
    @Override
    public List<Employee> allEmployees() {
        return new ArrayList<>(employeeMap.values());
    }
}



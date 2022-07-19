package pro.sky.java.course2.mockito.book;

import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.capitalize;

public class Employee {
    private final String firstName;
    private final String lastName;
    private final int dept;
    private final int salary;


    public Employee(String firstName, String lastName, int dept, int salary) {
        this.firstName = capitalize(firstName.toLowerCase());
        this.lastName = capitalize(lastName.toLowerCase());
        this.dept = dept;
        this.salary = salary;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getDept() {
        return dept;
    }

    public int getSalary() {
        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return dept == employee.dept && salary == employee.salary && Objects.equals(firstName, employee.firstName) && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dept, salary);
    }

    @Override
    public String toString() {
        return "Сотрудник: " +
                "Имя - " + firstName + '\'' +
                "Фамилия - " + lastName + '\'' +
                "Отдел - " + dept +
                "Довольствие - " + salary;
    }
}

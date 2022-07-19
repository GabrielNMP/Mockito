package pro.sky.java.course2.mockito.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.mockito.book.Employee;
import pro.sky.java.course2.mockito.service.EmployeeService;

import java.util.List;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam String firstName,
                                @RequestParam String lastName,
                                @RequestParam int dept,
                                @RequestParam int salary){
        return employeeService.addEmployee(firstName,lastName,dept,salary);
    }
    @GetMapping("/delete")
    public Employee deleteEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam int dept,
                                   @RequestParam int salary){
        return employeeService.deleteEmployee(firstName,lastName,dept,salary);
    }

    @GetMapping("/pass")
    public Employee passEmployee(@RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam int dept,
                                 @RequestParam int salary){
        return employeeService.passEmployee(firstName,lastName,dept,salary);
    }
    @GetMapping("/all")
    public List<Employee> allEmployees(){
        return employeeService.allEmployees();
    }

}

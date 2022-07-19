package pro.sky.java.course2.mockito.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.course2.mockito.book.Employee;
import pro.sky.java.course2.mockito.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/max-salary")
    public Employee maxSalary (@RequestParam int dept) {
        return departmentService.maxSalary(dept);
    }

    @GetMapping("/min-salary") public Employee minSalary (@RequestParam int dept) {
        return departmentService.minSalary(dept);
    }

    @GetMapping(value = "/all", params = "dept")
    public List<Employee> employeesFromDepartment (@RequestParam int dept){
        return departmentService.employeesFromDepartment(dept);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> allEmployeesByDepartments (){
        return departmentService.allEmployeesByDepartments();
    }
}

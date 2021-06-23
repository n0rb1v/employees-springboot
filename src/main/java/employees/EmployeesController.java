package employees;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employee")
public class EmployeesController {

    private EmployeeService employeeService;

    public EmployeesController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDto> listEmployees(@RequestParam Optional<String> prefix) {
        return employeeService.listEmployees(prefix);
    }

    @GetMapping("/{id}")
    public EmployeeDto findEmployeeById(@PathVariable("id") long id) {
        return employeeService.findEmployeeById(id);
    }

    @PostMapping
    public EmployeeDto createEmployee(@RequestBody CreateEmployeeCommand command){
        return employeeService.createEmployee(command);
    }

    @PutMapping("/{id}")
    public EmployeeDto updateEmployee(@PathVariable("id") long id, @RequestBody UpdateEmployeeCommand command) {
        return employeeService.updateEmployee(id,command);
    }
    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable("id") long id) {
        employeeService.deleteEmployee(id);
    }
}

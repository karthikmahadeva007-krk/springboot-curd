package com.krk.employee_management.Controller;

import org.springframework.web.bind.annotation.*;

import com.krk.employee_management.Entity.Employee;
import com.krk.employee_management.Service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping
    public Employee createEmployee(
            @RequestBody Employee employee) {

        return service.createEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAllEmployees() {

        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(
            @PathVariable Long id) {

        return service.getEmployeeById(id);
    }

    @PutMapping("/{id}")
    public Employee updateEmployee(
            @PathVariable Long id,
            @RequestBody Employee employee) {

        return service.updateEmployee(id, employee);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(
            @PathVariable Long id) {

        service.deleteEmployee(id);

        return "Employee deleted successfully";
    }
}
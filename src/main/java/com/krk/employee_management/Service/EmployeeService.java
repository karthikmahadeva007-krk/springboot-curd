package com.krk.employee_management.Service;

import org.springframework.stereotype.Service;

import com.krk.employee_management.Entity.Employee;
import com.krk.employee_management.Repo.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    public Employee createEmployee(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));
    }

    public Employee updateEmployee(Long id, Employee employee) {

        Employee existingEmployee = repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        existingEmployee.setName(employee.getName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setDepartment(employee.getDepartment());
        existingEmployee.setSalary(employee.getSalary());

        return repository.save(existingEmployee);
    }

    public void deleteEmployee(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Employee not found");
        }

        repository.deleteById(id);
    }
}
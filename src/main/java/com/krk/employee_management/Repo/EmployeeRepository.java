package com.krk.employee_management.Repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krk.employee_management.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
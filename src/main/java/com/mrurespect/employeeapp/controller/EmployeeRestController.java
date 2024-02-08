package com.mrurespect.employeeapp.controller;

import com.mrurespect.employeeapp.entity.Employee;
import com.mrurespect.employeeapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public List<Employee> findAll(){

        return employeeService.findAll();
    }
    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId) {
        Employee employee =employeeService.findById(employeeId);
        if (employee ==null){
            throw new EmployeeNotFoundException("employee id not found - "+employeeId);
        }
        return employee;
    }
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee){
        //in case of passing an id via json we gonna set it to 0 to do insert instead of an update
        employee.setId(0);

        return employeeService.save(employee); // it has updated id from DB in case of insert
    }
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        Employee dbEmployee =employeeService.save(employee);
        return employee;
    }
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee tempEmployee =employeeService.findById(employeeId);
        if (tempEmployee == null)throw  new EmployeeNotFoundException("employee id not found - "+employeeId);
        employeeService.deleteById(employeeId);
        return "deleted employee id - "+employeeId ;
    }
}

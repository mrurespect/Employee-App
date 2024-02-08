package com.mrurespect.employeeapp.controller;

import com.mrurespect.employeeapp.entity.Employee;
import com.mrurespect.employeeapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private int value =-1;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    @GetMapping("/list")
    public String listEmployees(Model model,@Autowired Authentication authentication){
        boolean isAdmin =authentication.getAuthorities().stream().anyMatch(auth -> "ROLE_ADMIN".equals(auth.getAuthority()));
        boolean isManager =authentication.getAuthorities().stream().anyMatch(auth -> "ROLE_MANAGER".equals(auth.getAuthority()));
        model.addAttribute("isAdmin",isAdmin);
        model.addAttribute("isManager",isManager);
        model.addAttribute("changeId",value);
        model.addAttribute("employees",employeeService.findAll());
        model.addAttribute(new Employee());
        System.out.println(model.getAttribute("authentication"));
        return "list-employee" ;
    }
    @GetMapping("/")
    public String redirect(){
        return "redirect:/employees/list" ;
    }
    @GetMapping("/addEmployee")
    public String addEmployee(Model model){
        model.addAttribute(new Employee());
        return "employee-form" ;
    }
    @GetMapping("/enableChange/{employeeId}")
    public String enable(@PathVariable int employeeId,Model model) {
        value = employeeId;
        return "redirect:/employees/list";

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
    public String addEmployee(Employee employee){//or @ModelAttribute("employee") Employee employee
        //in case of passing an id via json we gonna set it to 0 to do insert instead of an update
        //employee.setId(0);
        employeeService.save(employee);
          // it has updated id from DB in case of insert
        value=-1;
        return "redirect:/employees/list";
    }
    @PutMapping("/employees")
    public void updateEmployee(Employee employee){
        System.out.println(employee);
        employeeService.save(employee);
    }
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee tempEmployee =employeeService.findById(employeeId);
        if (tempEmployee == null)throw  new EmployeeNotFoundException("employee id not found - "+employeeId);
        employeeService.deleteById(employeeId);
        return "redirect:/employees/list" ;
    }
}

package com.mrurespect.employeeapp.dao;

import com.mrurespect.employeeapp.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int id);
    Employee save(Employee employee);
    void deleteByID(int id);


}

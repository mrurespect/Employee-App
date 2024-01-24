package com.mrurespect.employeeapp.dao;

import com.mrurespect.employeeapp.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDAOimpl implements EmployeeDAO {
    private final EntityManager entityManager ;

    @Autowired
    public EmployeeDAOimpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {
        TypedQuery<Employee> query =entityManager.createQuery("from Employee",Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class,id);
    }
    @Override  //we don t use @Transactionnal because it gonna be handlet at the service layer
    public Employee save(Employee employee) {
        return entityManager.merge(employee);
    }
    @Override
    public void deleteByID(int id) {
        Employee employee =entityManager.find(Employee.class,id);
        entityManager.remove(employee);
    }
}

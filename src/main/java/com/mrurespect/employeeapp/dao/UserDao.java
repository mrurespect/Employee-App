package com.mrurespect.employeeapp.dao;


import com.mrurespect.employeeapp.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    void save(User user);
    
}

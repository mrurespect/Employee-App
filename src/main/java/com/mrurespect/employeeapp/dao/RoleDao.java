package com.mrurespect.employeeapp.dao;


import com.mrurespect.employeeapp.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);
}

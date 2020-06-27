package com.pssdev.pss.dao;

import com.pssdev.pss.entity.Employee;

public interface EmployeeDao extends CURDDao<Employee, Integer> {
   Employee getByName(String name);
}

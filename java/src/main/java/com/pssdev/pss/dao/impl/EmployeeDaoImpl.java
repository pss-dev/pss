package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.EmployeeDao;
import com.pssdev.pss.entity.Employee;
import org.springframework.stereotype.Repository;

@Repository("employeeDao")
public class EmployeeDaoImpl extends BaseDao<Employee, Long> implements EmployeeDao {
  @Override
  protected Class<Employee> getClazz() {
    return Employee.class;
  }
}

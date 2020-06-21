package com.pssdev.pss.service.impl;

import com.pssdev.pss.entity.Employee;
import com.pssdev.pss.dao.EmployeeDao;
import com.pssdev.pss.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
  @Autowired
  private EmployeeDao employeeDao;

  @Override
  public Employee getEmployee(Long id) {
    return employeeDao.get(id);
  }

  @Override
  public List<Employee> getEmployees() {
    return employeeDao.getAll();
  }

  @Override
  public void insertEmployee(Employee employee) {
    employeeDao.insert(employee);
  }

  @Override
  public void updateEmployee(Employee employee) {
    employeeDao.update(employee);
  }

  @Override
  public void deleteEmployee(Employee employee) {
    employeeDao.delete(employee);
  }
}

package com.pssdev.pss.service.impl;

import com.pssdev.pss.annotation.Audit;
import com.pssdev.pss.annotation.AuditObject;
import com.pssdev.pss.dao.EmployeeDao;
import com.pssdev.pss.entity.Employee;
import com.pssdev.pss.service.EmployeeService;
import com.pssdev.pss.util.ActionType;
import com.pssdev.pss.util.ResourceEnum;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {
  @Autowired
  public EmployeeServiceImpl(EmployeeDao employeeDao) {
    this.employeeDao = employeeDao;
  }

  @Transactional(readOnly = true)
  @Override
  public Employee getEmployee(Integer id) {
    return employeeDao.get(id);
  }

  @Transactional
  @Override
  public Employee getEmployeeByName(String name) {
    return employeeDao.getByName(name);
  }

  @Transactional
  @Override
  public Employee getCurrentEmployee() {
    Subject subject = SecurityUtils.getSubject();
    Object principal = subject.getPrincipal();
    Employee employee = null;

    if (principal != null) {
      employee = getEmployeeByName(principal.toString());
    }

    return employee;
  }

  @Transactional(readOnly = true)
  @Override
  public List<Employee> getEmployees() {
    return employeeDao.getAll();
  }

  @Audit(value = ResourceEnum.EMPLOYEE)
  @Transactional
  @Override
  public void insertEmployee(@AuditObject("getName()") Employee employee) {
    employeeDao.insert(employee);
  }

  @Audit(value = ResourceEnum.EMPLOYEE, actionType = ActionType.MODIFY)
  @Transactional
  @Override
  public void updateEmployee(@AuditObject("getName()") Employee employee) {
    employeeDao.update(employee);
  }

  @Audit(value = ResourceEnum.EMPLOYEE, actionType = ActionType.DELETE)
  @Transactional
  @Override
  public void deleteEmployee(@AuditObject("getName()") Employee employee) {
    employeeDao.delete(employee);
  }

  private final EmployeeDao employeeDao;
}

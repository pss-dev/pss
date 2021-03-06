package com.pssdev.pss.service;

import com.pssdev.pss.entity.Employee;

import java.util.List;

public interface EmployeeService {
  /**
   * get user by id
   *
   * @return
   */
  Employee getEmployee(Integer id);

  /**
   * get user by name
   *
   * @return
   */
  Employee getEmployeeByName(String name);

  /**
   * get current
   *
   * @return
   */
  Employee getCurrentEmployee();

  /**
   * get all Users
   *
   * @return
   */
  List<Employee> getEmployees();

  /**
   * insert a user
   *
   * @param user
   */
  void insertEmployee(Employee user);

  /**
   * update user
   *
   * @param user
   */
  void updateEmployee(Employee user);

  /**
   * delete a user
   */
  void deleteEmployee(Employee user);
}

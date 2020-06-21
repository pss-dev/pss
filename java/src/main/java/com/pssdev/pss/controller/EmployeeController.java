package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Employee;
import com.pssdev.pss.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {
  @Autowired
  private EmployeeService userService;

  @GetMapping("employee/getAll")
  @ResponseBody
  public List<Employee> getAllEmployees() {
    return userService.getEmployees();
  }

  @PutMapping("employee/add")
  @ResponseBody
  public void addEmployee(Employee user) {
    userService.insertEmployee(user);
  }
}

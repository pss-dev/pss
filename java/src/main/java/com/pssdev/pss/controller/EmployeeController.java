package com.pssdev.pss.controller;

import com.pssdev.pss.util.*;
import com.pssdev.pss.entity.Employee;
import com.pssdev.pss.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/1.0/")
public class EmployeeController {
  @Autowired
  private EmployeeService employeeService;

  @GetMapping("employee")
  @ResponseBody
  public List<Employee> getAllEmployees() {
    return employeeService.getEmployees();
  }

  @PostMapping("employee")
  @ResponseBody
  public void addEmployee(@RequestBody Employee user) {
    String md5Password = SecurityUtil.generatorPassword(user.getAccount(), user.getPassword());
    user.setPassword(md5Password);

    employeeService.insertEmployee(user);
  }

  @PutMapping("employee")
  @ResponseBody
  public void updateEmployee(@RequestBody Employee user) {
    if (user.getPasswordChange()) {
      String md5Password = SecurityUtil.generatorPassword(user.getAccount(), user.getPassword());
      user.setPassword(md5Password);
    }

    employeeService.updateEmployee(user);
  }

  @DeleteMapping("employee")
  @ResponseBody
  public void deleteEmployee(@RequestBody Employee user) {
    employeeService.deleteEmployee(user);
  }
}

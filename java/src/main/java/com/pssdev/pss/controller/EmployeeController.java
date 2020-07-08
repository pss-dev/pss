package com.pssdev.pss.controller;

import com.pssdev.pss.util.*;
import com.pssdev.pss.entity.Employee;
import com.pssdev.pss.service.EmployeeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0")
public class EmployeeController {
  @Autowired
  private EmployeeService employeeService;

  @GetMapping("/employee")
  @RequiresPermissions("*:2048:r")
  @ResponseBody
  public List<Employee> getAllEmployees() {
    return employeeService.getEmployees();
  }

  @PostMapping("/employee")
  @RequiresPermissions("*:2048:w")
  public void addEmployee(@RequestBody Employee user) {
    String md5Password = SecurityUtil.generatorPassword(user.getAccount(), user.getPassword());
    user.setPassword(md5Password);

    employeeService.insertEmployee(user);
  }

  @PutMapping("/employee")
  @RequiresPermissions("*:2048:w")
  public void updateEmployee(@RequestBody Employee user) {
    if (user.getPasswordChange()) {
      String md5Password = SecurityUtil.generatorPassword(user.getAccount(), user.getPassword());
      user.setPassword(md5Password);
    }

    employeeService.updateEmployee(user);
  }

  @DeleteMapping("/employee")
  @RequiresPermissions("*:2048:d")
  public void deleteEmployee(@RequestBody Employee user) {
    employeeService.deleteEmployee(user);
  }
}

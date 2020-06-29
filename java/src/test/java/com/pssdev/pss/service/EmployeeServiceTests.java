package com.pssdev.pss.service;

import com.pssdev.pss.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmployeeServiceTests {

   @Autowired
   private EmployeeService employeeService;

   @Test
   public void testGetEmployeeByName() {
      String empName = "admin";

      Employee employee = employeeService.getEmployeeByName(empName);

      Assertions.assertNotNull(employee, "admin is null.");
   }

   @Test
   public void testGetEmployee() {
      int empId = 1;

      Employee employee = employeeService.getEmployee(empId);

      Assertions.assertNotNull(employee, "Query Employee Error By id: " + empId);
   }
}

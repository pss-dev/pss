package com.pssdev.pss.service;

import com.pssdev.pss.entity.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
public class DepartmentServiceTests {

   @Autowired
   private DepartmentService departmentService;

   @Test
   public void testNonNull() {
      Assertions.assertNotNull(departmentService, "Init Department Dao Error.");
   }

   @Test
   public void testInsertTopDepartment() {
      Department dept1 = new Department();
      dept1.setName("Top Level");
      dept1.setInitials("TL");

      departmentService.insertDepartment(dept1);
   }

   @Test
   public void testGetDepartments() {
      List<Department> departments = departmentService.getDepartments();

      Assertions.assertNotNull(departments, "Departments is empty");
   }

   @Test
   public void testGetDepartment() {
      final int ID = 2;
      Department department = departmentService.getDepartment(ID);

      Assertions.assertNotNull(department, "Department is null");
      Assertions.assertEquals(department.getId(), ID, "Get department error.");

      LOGGER.info("Department: {}", department);
   }

   @Test
   public void testInsertChildDepartment() {
      final int ID = 2;
      Department parentDept = departmentService.getDepartment(ID);

      LOGGER.info("Query parent dept first: {}", parentDept);

      Department cDept1 = new Department();
      cDept1.setName("Child Level1");
      cDept1.setInitials("CL1");
      cDept1.setParent(parentDept);

      Department cDept2 = new Department();
      cDept2.setName("Child Level2");
      cDept2.setInitials("CL2");
      cDept2.setParent(parentDept);

      // insert children
      int cid1 = departmentService.insertDepartment(cDept1);
      int cid2 = departmentService.insertDepartment(cDept2);

      LOGGER.info("Insert dept {}, {}.", cid1, cid2);

      // get parent again
      parentDept = departmentService.getDepartment(ID);

      LOGGER.info("Query parent dept again: {}", parentDept);

      Set<Department> children = parentDept.getChildren();

      Assertions.assertFalse(
         children == null || children.size() < 1, "Query children error.");

      Set<Integer> childIds = children.stream().map(d -> d.getId()).collect(Collectors.toSet());

      Assertions.assertTrue(childIds.contains(cid1), "Missing child 1");
      Assertions.assertTrue(childIds.contains(cid2), "Missing child 2");
   }

   private static final Logger LOGGER
      = LoggerFactory.getLogger(DepartmentServiceTests.class);
}

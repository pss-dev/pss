package com.pssdev.pss.service;

import com.pssdev.pss.entity.Department;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DepartmentServiceTests {

  @Autowired
  private DepartmentService departmentService;

  @Test
  @Order(1)
  public void testNonNull() {
    Assertions.assertNotNull(departmentService, "Init Department Dao Error.");
  }

  @Order(2)
  @RepeatedTest(value = 5, name = "Insert 5 dept to make sure get dept id not null")
  public void testInsertTopDepartment() {
    int index = (int) (1000 * Math.random());
    Department dept1 = new Department();
    dept1.setName("Top Level" + index);
    dept1.setInitials("TL" + index);

    departmentService.insertDepartment(dept1);
  }

  @Test
  @Order(3)
  public void testGetDepartments() {
    List<Department> departments = departmentService.getDepartments();

    LOGGER.info("Get All Departments: {}", departments);

    Assertions.assertNotNull(departments, "Departments is empty");
  }

  @ParameterizedTest
  @ValueSource(ints = { 2 })
  @Order(4)
  public void testGetDepartment(int id) {
    Department department = departmentService.getDepartment(id);

    Assertions.assertNotNull(department, "Department is null");
    Assertions.assertEquals(department.getId(), id, "Get department error.");

    LOGGER.info("Department: {}", department);
  }

  @ParameterizedTest
  @ValueSource(ints = { 2 })
  @Order(5)
  public void testInsertChildDepartment(int id) {
    Department parentDept = departmentService.getDepartment(id);

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
    parentDept = departmentService.getDepartment(id);

    LOGGER.info("Query parent dept again: {}", parentDept);

    List<Department> children = parentDept.getChildren();

    Assertions.assertFalse(children == null || children.size() < 1, "Query children error.");

    Set<Integer> childIds = children.stream().map(d -> d.getId()).collect(Collectors.toSet());

    Assertions.assertTrue(childIds.contains(cid1), "Missing child 1");
    Assertions.assertTrue(childIds.contains(cid2), "Missing child 2");
  }

   @ParameterizedTest
   @ValueSource(ints = { 2 })
   @Order(6)
   public void testDeleteDepartment(int id) {
     Department dept = departmentService.getDepartment(id);
     List<Department> children = dept.getChildren();

     if(children != null) {
       children.forEach(departmentService::deleteDepartment);
     }

     departmentService.deleteDepartment(new Department(id));
   }

  @RepeatedTest(value = 3, name = "Query 3 times to see sql display times.")
  public void testDepartmentCache() {
    List<Department> departments = departmentService.getDepartments();

    LOGGER.info("Get All Departments: {}", departments);

    if(departments != null) {
      departments.stream()
         .map(Department::getId)
         .forEach(departmentService::getDepartment);
    }
  }

  private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceTests.class);
}

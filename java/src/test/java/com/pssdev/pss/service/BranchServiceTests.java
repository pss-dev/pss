package com.pssdev.pss.service;

import com.pssdev.pss.entity.Branch;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@Disabled("For build failed. Should be executed in order.")
@SpringBootTest
public class BranchServiceTests {
  @Autowired
  private BranchService branchService;

  @Test
  public void testNonNull() {
    Assertions.assertNotNull(branchService, "Init branch Dao Error");
  }

  @Test
  public void testInsertBranch() {
    Branch branch = new Branch();
    branch.setName("branch1");
    Integer id = branchService.insertBranch(branch);
    LOGGER.info("insert branch id: " + id);
  }

  @Test
  public void testGetBranches() {
    List<Branch> branches = branchService.getBranches();

    LOGGER.info("Query all branches: {}", branches);

    Assertions.assertNotNull(branches, "Branches is Empty");
  }

  @Test
  public void testUpdateBranch() {
    Integer fatherId = 1;
    Branch branch = branchService.getBranch(fatherId);
    Set<Branch> children = new HashSet<>();
    Branch child1 = new Branch();
    child1.setName("child1");
    children.add(child1);
    branch.setChildren(children);
    branchService.updateBranch(branch);

    Branch newBranch = branchService.getBranch(fatherId);
    Assertions.assertEquals(children, newBranch.getChildren());
  }

  // @ParameterizedTest
  // @ValueSource(ints = 1)
  // public void testDeleteBranch(Branch branch) {
  // branchService.deleteBranch(branch);
  // }

  @RepeatedTest(value = 3, name = "Query 3 times to see sql display times.")
  public void testBranchCache() {
    List<Branch> branches = branchService.getBranches();

    LOGGER.info("Get All Departments: {}", branches);

    if(branches != null) {
      branches.stream()
         .map(Branch::getId)
         .forEach(branchService::getBranch);
    }
  }

  private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentServiceTests.class);

}

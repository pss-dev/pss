package com.pssdev.pss.service;

import com.pssdev.pss.entity.Branch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        List branches = branchService.getBranches();
        Assertions.assertNotNull(branches, "Branches is Empty");
    }

    private static final Logger LOGGER
            = LoggerFactory.getLogger(DepartmentServiceTests.class);

}

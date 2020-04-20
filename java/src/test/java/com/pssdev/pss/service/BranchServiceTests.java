package com.pssdev.pss.service;

import com.pssdev.pss.entity.Branch;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Test
    public void testUpdateBranch() {
        Integer fatherId = 1;
        Branch branch = branchService.getBranch(fatherId);
        Set children = new HashSet<>();
        Branch child1 = new Branch();
        child1.setName("child1");
        children.add(child1);
        branch.setChildren(children);
        branchService.updateBranch(branch);

        Branch newBranch = branchService.getBranch(fatherId);
        Assertions.assertEquals(children, newBranch.getChildren());
    }

    @ParameterizedTest
    @ValueSource(ints = 1)
    public void testDeleteBranch(int id) {
        branchService.deleteBranch(id);
    }

    private static final Logger LOGGER
            = LoggerFactory.getLogger(DepartmentServiceTests.class);

}

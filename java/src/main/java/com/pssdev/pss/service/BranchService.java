package com.pssdev.pss.service;

import com.pssdev.pss.entity.Branch;

import java.util.List;

public interface BranchService {
    /**
     * get branch by father id
     * @return
     */
    List<Branch> getBranch(Integer fatherID);

    /**
     * get all Branches
     * @return
     */
    List<Branch> getBranches();

    /**
     * insert a branch
     * @param branch
     */
    Integer insertBranch(Branch branch);

    /**
     * update branch
     * @param branch
     */
    void updateBranch(Branch branch);

    /**
     * delete a branch by id
     * @param id
     */
    void deleteBranch(Integer id);
}

package com.pssdev.pss.dao;

import com.pssdev.pss.entity.Branch;

import java.util.List;

public interface BranchDao {
    public List<Branch> getAllBranch();
    public Branch getBranch(Integer fatherID);
    public Integer insertBranch(Branch branch);
    public void deleteBranch(Integer branchID);
    public void updateBranch(Branch branch);
}

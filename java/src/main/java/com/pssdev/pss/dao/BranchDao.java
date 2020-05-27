package com.pssdev.pss.dao;

import com.pssdev.pss.entity.Branch;

import java.util.List;

public interface BranchDao {
  public List<Branch> getAllBranch();

  public Branch getBranch(Integer fatherID);

  public Integer insertBranch(Branch branch);

  public void deleteBranch(Branch branch);

  public void updateBranch(Branch branch);
}

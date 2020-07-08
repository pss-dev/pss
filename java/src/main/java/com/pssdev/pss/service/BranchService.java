package com.pssdev.pss.service;

import com.pssdev.pss.entity.Branch;

import java.util.List;

public interface BranchService extends TreeStructureService<Branch> {
  /**
   * get branch by father id
   *
   * @return
   */
  Branch getBranch(Integer fatherID);

  /**
   * get all Branches
   *
   * @return
   * @param fatherID
   */
  List<Branch> getBranches(Integer fatherID);

  /**
   * insert a branch
   *
   * @param branch
   */
  Integer insertBranch(Branch branch);

  /**
   * update branch
   *
   * @param branch
   */
  void updateBranch(Branch branch);

  /**
   * delete a branch by id
   */
  void deleteBranch(Branch branch);

  /**
   * get branch by name
   *
   * @return
   */
  Branch getBranchByName(String name);
}

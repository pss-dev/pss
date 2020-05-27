package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.BranchDao;
import com.pssdev.pss.entity.Branch;
import com.pssdev.pss.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {
  @Autowired
  public BranchServiceImpl(BranchDao branchDao) {
    this.branchDao = branchDao;
  }

  private BranchDao branchDao;

  @Transactional(readOnly = true)
  @Override
  public Branch getBranch(Integer fatherID) {
    return branchDao.getBranch(fatherID);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Branch> getBranches() {
    return branchDao.getAllBranch();
  }

  @Transactional(readOnly = true)
  @Override
  public Integer insertBranch(Branch branch) {
    return branchDao.insertBranch(branch);
  }

  @Transactional(readOnly = true)
  @Override
  public void updateBranch(Branch branch) {
    branchDao.updateBranch(branch);
  }

  @Transactional(readOnly = true)
  @Override
  public void deleteBranch(Branch branch) {
    branchDao.deleteBranch(branch);
  }
}

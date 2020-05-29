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
    return branchDao.get(fatherID);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Branch> getBranches() {
    return branchDao.getAll();
  }

  @Transactional(readOnly = true)
  @Override
  public Integer insertBranch(Branch branch) {
    return branchDao.insert(branch);
  }

  @Transactional(readOnly = true)
  @Override
  public void updateBranch(Branch branch) {
    branchDao.update(branch);
  }

  @Transactional(readOnly = true)
  @Override
  public void deleteBranch(Branch branch) {
    branchDao.delete(branch);
  }
}

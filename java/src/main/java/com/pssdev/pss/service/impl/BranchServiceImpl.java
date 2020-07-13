package com.pssdev.pss.service.impl;

import com.pssdev.pss.annotation.Audit;
import com.pssdev.pss.dao.BranchDao;
import com.pssdev.pss.entity.Branch;
import com.pssdev.pss.service.BranchService;
import com.pssdev.pss.util.ActionType;
import com.pssdev.pss.util.ResourceEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
  public List<Branch> getBranches(Integer fatherID) {
    if (fatherID == null) {
      return branchDao.getAll();
    } else if (TOP_FLAG1.equals(fatherID) || TOP_FLAG2.equals(fatherID)) {
      return branchDao.getTop();
    }

    Branch department = branchDao.get(fatherID);

    if (department != null) {
      return new ArrayList<>(department.getChildren());
    }

    return null;
  }

  @Audit(value = ResourceEnum.BRANCH)
  @Transactional
  @Override
  public Integer insertBranch(Branch branch) {
    return branchDao.insert(branch);
  }

  @Audit(value = ResourceEnum.BRANCH, actionType = ActionType.MODIFY)
  @Transactional
  @Override
  public void updateBranch(Branch branch) {
    branchDao.update(branch);
  }

  @Audit(value = ResourceEnum.BRANCH, actionType = ActionType.DELETE)
  @Transactional
  @Override
  public void deleteBranch(Branch branch) {
    branchDao.delete(branch);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Branch> getTop() {
    return branchDao.getTop();
  }

  @Transactional
  @Override
  public Branch getBranchByName(String name) {
    return branchDao.getByName(name);
  }
}

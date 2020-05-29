package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.BranchDao;
import com.pssdev.pss.entity.Branch;
import org.springframework.stereotype.Repository;

@Repository("branchDao")
public class BranchDaoImpl extends BaseDao<Branch, Integer> implements BranchDao {

  @Override
  protected Class<Branch> getClazz() {
    return Branch.class;
  }
}

package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.BranchDao;
import com.pssdev.pss.entity.Branch;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository("branchDao")
public class BranchDaoImpl extends BaseDao implements BranchDao {
  @Override
  public List<Branch> getAllBranch() {
    Session session = getSession();
    CriteriaQuery<Branch> criteriaQuery = session.getCriteriaBuilder().createQuery(Branch.class);
    criteriaQuery.from(Branch.class);
    return session.createQuery(criteriaQuery).list();
  }

  @Override
  public Branch getBranch(Integer fatherID) {
    return (Branch) getSession().load(Branch.class, fatherID);
  }

  @Override
  public Integer insertBranch(Branch branch) {
    return (Integer) getSession().save(branch);
  }

  @Override
  public void updateBranch(Branch branch) {
    getSession().update(branch);
  }

  @Override
  public void deleteBranch(Branch branch) {
    getSession().delete(branch);
  }
}

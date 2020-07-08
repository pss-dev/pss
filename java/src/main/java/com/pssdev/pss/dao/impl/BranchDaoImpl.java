package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.BranchDao;
import com.pssdev.pss.entity.Branch;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import javax.persistence.criteria.*;

@Repository("branchDao")
public class BranchDaoImpl extends TreeStructureDaoImpl<Branch, Integer> implements BranchDao {

  @Override
  protected Class<Branch> getClazz() {
    return Branch.class;
  }

  @Override
  public Branch getByName(String name) {
    Session session = getSession();

    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Branch> query = cb.createQuery(getClazz());
    Root<Branch> root = query.from(getClazz());
    query.where(cb.equal(root.get("name"), name));

    return session.createQuery(query).uniqueResultOptional().orElse(null);
  }
}

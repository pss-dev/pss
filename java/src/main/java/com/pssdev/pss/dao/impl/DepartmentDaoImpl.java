package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.DepartmentDao;
import com.pssdev.pss.entity.Department;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import javax.persistence.criteria.*;

@Repository("departmentDao")
public class DepartmentDaoImpl extends TreeStructureDaoImpl<Department, Integer> implements DepartmentDao {
  @Override
  protected Class<Department> getClazz() {
    return Department.class;
  }

  @Override
  public Department getByName(String name) {
    Session session = getSession();

    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Department> query = cb.createQuery(getClazz());
    Root<Department> root = query.from(getClazz());
    query.where(cb.equal(root.get("name"), name));

    return session.createQuery(query).uniqueResultOptional().orElse(null);
  }
}

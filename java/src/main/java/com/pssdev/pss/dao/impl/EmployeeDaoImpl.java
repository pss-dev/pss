package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.EmployeeDao;
import com.pssdev.pss.entity.Employee;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;

@Repository("employeeDao")
public class EmployeeDaoImpl extends BaseDao<Employee, Integer> implements EmployeeDao {
  @Override
  protected Class<Employee> getClazz() {
    return Employee.class;
  }

  @Override
  public Employee getByName(String name) {
    Session session = getSession();

    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Employee> query = cb.createQuery(getClazz());
    Root<Employee> root = query.from(getClazz());
    query.where(cb.equal(root.get("account"), name));

    return session.createQuery(query).uniqueResultOptional().orElse(null);
  }
}

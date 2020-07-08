package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.RoleDao;
import com.pssdev.pss.entity.Role;
import org.springframework.stereotype.Repository;
import org.hibernate.Session;

import javax.persistence.criteria.*;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDao<Role, Integer> implements RoleDao {
  @Override
  protected Class<Role> getClazz() {
    return Role.class;
  }

  @Override
  public Role getByName(String name) {
    Session session = getSession();

    CriteriaBuilder cb = session.getCriteriaBuilder();
    CriteriaQuery<Role> query = cb.createQuery(getClazz());
    Root<Role> root = query.from(getClazz());
    query.where(cb.equal(root.get("name"), name));

    return session.createQuery(query).uniqueResultOptional().orElse(null);
  }
}

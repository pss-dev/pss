package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.RoleDao;
import com.pssdev.pss.entity.Role;
import org.springframework.stereotype.Repository;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDao<Role, Integer> implements RoleDao {
   @Override
   protected Class<Role> getClazz() {
      return Role.class;
   }
}

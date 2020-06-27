package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.PermissionDao;
import com.pssdev.pss.entity.Permission;
import org.springframework.stereotype.Repository;

@Repository("permissionDao")
public class PermissionDaoImpl extends BaseDao<Permission, Integer> implements PermissionDao {
   @Override
   protected Class<Permission> getClazz() {
      return Permission.class;
   }
}

package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.PermissionDao;
import com.pssdev.pss.entity.Permission;
import com.pssdev.pss.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

   @Autowired
   public PermissionServiceImpl(PermissionDao permissionDao) {
      this.permissionDao = permissionDao;
   }

   @Transactional
   @Override
   public void insertPermission(Permission permission) throws Exception {
      permissionDao.insert(permission);
   }

   @Transactional
   @Override
   public void deletePermission(Permission permission) {
      permissionDao.delete(permission);
   }

   @Transactional
   @Override
   public void modifyPermission(Permission permission) throws Exception {
      permissionDao.update(permission);
   }

   @Transactional(readOnly = true)
   @Override
   public Permission getPermission(Integer id) {
      return permissionDao.get(id);
   }

   private final PermissionDao permissionDao;
}

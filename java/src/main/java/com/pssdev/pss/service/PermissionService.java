package com.pssdev.pss.service;

import com.pssdev.pss.entity.Permission;

public interface PermissionService {
   void insertPermission(Permission permission) throws Exception;

   void deletePermission(Permission permission);

   void modifyPermission(Permission permission) throws Exception;

   Permission getPermission(Integer id);
}

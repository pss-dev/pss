package com.pssdev.pss.service;

import com.pssdev.pss.entity.Role;

public interface RoleService {
   void insertRole(Role role) throws Exception;

   void deleteRole(Role role);

   void modifyRole(Role role) throws Exception;

   Role getRole(Integer id);
}

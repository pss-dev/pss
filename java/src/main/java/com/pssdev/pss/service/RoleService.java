package com.pssdev.pss.service;

import com.pssdev.pss.entity.Role;

import java.util.List;

public interface RoleService {
  void insertRole(Role role) throws Exception;

  void deleteRole(Role role);

  void modifyRole(Role role) throws Exception;

  Role getRole(Integer id);

  List<Role> getRoles();

  /**
   * get role by name
   *
   * @return
   */
  Role getRoleByName(String name);
}

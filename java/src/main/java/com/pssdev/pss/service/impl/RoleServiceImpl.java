package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.RoleDao;
import com.pssdev.pss.entity.Role;
import com.pssdev.pss.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

  @Autowired
  public RoleServiceImpl(RoleDao roleDao) {
    this.roleDao = roleDao;
  }

  @Transactional
  @Override
  public void insertRole(Role role) throws Exception {
    roleDao.insert(role);
  }

  @Transactional
  @Override
  public void deleteRole(Role role) {
    roleDao.delete(role);
  }

  @Transactional
  @Override
  public void modifyRole(Role role) throws Exception {
    roleDao.update(role);
  }

  @Transactional(readOnly = true)
  @Override
  public Role getRole(Integer id) {
    return roleDao.get(id);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Role> getRoles() {
    return roleDao.getAll();
  }

  @Transactional
  @Override
  public Role getRoleByName(String name) {
    return roleDao.getByName(name);
  }

  private final RoleDao roleDao;
}

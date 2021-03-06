package com.pssdev.pss.service.impl;

import com.pssdev.pss.annotation.Audit;
import com.pssdev.pss.annotation.AuditObject;
import com.pssdev.pss.dao.RoleDao;
import com.pssdev.pss.entity.Role;
import com.pssdev.pss.service.RoleService;
import com.pssdev.pss.util.ActionType;
import com.pssdev.pss.util.ResourceEnum;

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

  @Audit(value = ResourceEnum.ROLE)
  @Transactional
  @Override
  public void insertRole(@AuditObject("getName()") Role role) throws Exception {
    roleDao.insert(role);
  }

  @Audit(value = ResourceEnum.ROLE, actionType = ActionType.DELETE)
  @Transactional
  @Override
  public void deleteRole(@AuditObject("getName()") Role role) {
    roleDao.delete(role);
  }

  @Audit(value = ResourceEnum.ROLE, actionType = ActionType.MODIFY)
  @Transactional
  @Override
  public void modifyRole(@AuditObject("getName()") Role role) throws Exception {
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

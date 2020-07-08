package com.pssdev.pss.controller;

import com.pssdev.pss.service.RoleService;
import com.pssdev.pss.entity.Role;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0")
public class RoleController {
  @Autowired
  private RoleService roleService;

  @PostMapping("/role")
  @RequiresPermissions("*:1024:w")
  public void insertRole(@RequestBody Role role) throws Exception {
    roleService.insertRole(role);
  }

  @PostMapping("/role/duplicate")
  @RequiresPermissions("*:1024:r")
  public boolean checkRoleDuplicate(@RequestBody Role role) throws Exception {
    Role rol = roleService.getRoleByName(role.getName());

    return rol == null;
  }

  @DeleteMapping("/role")
  @RequiresPermissions("*:1024:d")
  public void deleteRole(@RequestBody Role role) {
    roleService.deleteRole(role);
  }

  @PutMapping("/role")
  @RequiresPermissions("*:1024:w")
  public void modifyRole(@RequestBody Role role) throws Exception {
    roleService.modifyRole(role);
  }

  @GetMapping("/role")
  @RequiresPermissions("*:1024:r")
  public List<Role> getRoles() {
    return roleService.getRoles();
  }
}

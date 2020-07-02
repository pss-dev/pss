package com.pssdev.pss.controller;

import com.pssdev.pss.service.RoleService;
import com.pssdev.pss.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0")
public class RoleController {
  @Autowired
  private RoleService roleService;

  @PostMapping("/role")
  public void insertRole(@RequestBody Role role) throws Exception {
    roleService.insertRole(role);
  }

  @DeleteMapping("/role")
  public void deleteRole(@RequestBody Role role) {
    roleService.deleteRole(role);
  }

  @PutMapping("/role")
  public void modifyRole(@RequestBody Role role) throws Exception {
    roleService.modifyRole(role);
  }

  @GetMapping("/role")
  public List<Role> getRoles() {
    return roleService.getRoles();
  }
}

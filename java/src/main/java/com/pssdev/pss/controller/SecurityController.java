package com.pssdev.pss.controller;

import com.pssdev.pss.entity.*;
import com.pssdev.pss.service.EmployeeService;
import com.pssdev.pss.util.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

@Controller
public class SecurityController {

  @Autowired
  public SecurityController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @GetMapping("/login")
  public String gotoLoginPage() {
    return "login";
  }

  @PostMapping("/api/1.0/login")
  public String login(@RequestParam String userName, @RequestParam String password,
      @RequestParam(required = false) boolean rememberMe) {
    Subject currentUser = SecurityUtils.getSubject();

    // 如果用户没有登录
    if (!currentUser.isAuthenticated()) {
      // 封装用户和密码为 UsernamePasswordToken
      UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

      // 设置记住我, 在整个会话中记住身份.
      token.setRememberMe(rememberMe);

      try {
        // 执行登录
        currentUser.login(token);
      } catch (AuthenticationException e) {
        throw e;
      }
    }

    // 登录成功跳转到 / 页面
    return "redirect:/";
  }

  @GetMapping("/api/1.0/principal")
  @ResponseBody
  public String getCurrentEmployee() {
    Subject subject = SecurityUtils.getSubject();
    Object principal = subject.getPrincipal();
    Employee employee = null;
    String userName = "";

    if (principal != null) {
      employee = employeeService.getEmployeeByName(principal.toString());
    }

    if (employee != null) {
      userName = employee.getName();
    }

    return userName;
  }

  @GetMapping("/error")
  public String gotoErrorPage() {
    return "/error/exception";
  }

  @GetMapping("/api/1.0/permission")
  @ResponseBody
  public List<Permission> getPermission() {
    Employee employee = employeeService.getCurrentEmployee();

    if (employee == null) {
      return null;
    }

    Set<Role> roles = employee.getRoles();
    List<Permission> permissions = new ArrayList<>();
    Hashtable<String, Permission> pset = new Hashtable<>();
    List<Permission> res = new ArrayList<>();
    boolean haveAdminRole = false;

    for (Role role : roles) {
      if (SecurityUtil.Administrator.equals(role.getName())) {
        haveAdminRole = true;
        break;
      }
      Set<Permission> perms = role.getPermissions();
      permissions.addAll(perms);
    }

    System.out.println("====== haveAdminRole " + haveAdminRole);
    if (haveAdminRole) {
      setAdminPermissions(res);
    } else {
      for (Permission permission : permissions) {
        mergePermission(permission, pset);
      }

      Permission baseInfo = pset.get(ResourceEnum.BASEINFO);
      Permission manage = pset.get(ResourceEnum.MANAGE);
      Permission statistic = pset.get(ResourceEnum.STATISTIC);

      String[] baseInfoChildren = { ResourceEnum.PRODUCT, ResourceEnum.UNIT, ResourceEnum.PRICE, ResourceEnum.COMPANY,
          ResourceEnum.DEPOT, ResourceEnum.DEPARTMENT, ResourceEnum.BRANCH };
      setPermission(res, baseInfoChildren, pset, baseInfo);

      Permission orderForm = pset.get(ResourceEnum.ORDERFORM);
      if (orderForm != null && orderForm.getOperator() != 0) {
        res.add(orderForm);
      }

      String[] manageChildren = { ResourceEnum.ROLE, ResourceEnum.EMPLOYEE, ResourceEnum.ACCOUNT };
      setPermission(res, manageChildren, pset, manage);

      String[] statisticChildren = { ResourceEnum.LOG, ResourceEnum.REVENUE };
      setPermission(res, statisticChildren, pset, statistic);
    }

    return res;
  }

  private void setPermission(List<Permission> res, String[] names, Hashtable<String, Permission> pset,
      Permission fperm) {
    for (String name : names) {
      Permission nperm = pset.get(name);

      if (nperm == null && fperm == null) {
        continue;
      }

      if (nperm != null && fperm != null) {
        nperm.setOperator(nperm.getOperator() | fperm.getOperator());
      } else if (nperm == null && fperm != null) {
        nperm = new Permission();
        nperm.setOperator(fperm.getOperator());
        nperm.setResource(name);
      }

      if (((nperm.getOperator() & PermissionEnum.READ.getPermission()) != 0)
          || ((nperm.getOperator() & PermissionEnum.ADMIN.getPermission()) == PermissionEnum.ADMIN.getPermission())) {
        res.add(nperm);
      }
    }
  }

  private void mergePermission(Permission permission, Hashtable<String, Permission> value) {
    Permission p = value.get(permission.getResource());

    if (p == null) {
      value.put(permission.getResource(), permission);
    } else {
      p.setOperator(p.getOperator() | permission.getOperator());
    }
  }

  private void setAdminPermissions(List<Permission> res) {
    String[] names = { ResourceEnum.PRODUCT, ResourceEnum.UNIT, ResourceEnum.PRICE, ResourceEnum.COMPANY,
        ResourceEnum.DEPOT, ResourceEnum.DEPARTMENT, ResourceEnum.BRANCH, ResourceEnum.ORDERFORM, ResourceEnum.ROLE,
        ResourceEnum.EMPLOYEE, ResourceEnum.ACCOUNT, ResourceEnum.LOG, ResourceEnum.REVENUE };

    for (String name : names) {
      Permission nperm = new Permission();
      nperm.setResource(name);
      nperm.setOperator(PermissionEnum.ADMIN.getPermission());
      res.add(nperm);
    }
  }

  private final EmployeeService employeeService;
}

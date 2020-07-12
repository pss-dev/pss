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

import java.util.*;

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

    return Objects.toString(principal, "");
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
    Map<Integer, Permission> pset = new HashMap<>();
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

    if (haveAdminRole) {
      setAdminPermissions(res);
    } else {
      for (Permission permission : permissions) {
        mergePermission(permission, pset);
      }

      Permission baseInfo = pset.get(ResourceEnum.BASEINFO.getType());
      Permission manage = pset.get(ResourceEnum.MANAGE.getType());
      Permission statistic = pset.get(ResourceEnum.STATISTIC.getType());

      int[] baseInfoChildren = { ResourceEnum.PRODUCT.getType(),
         ResourceEnum.UNIT.getType(), ResourceEnum.PRICE.getType(), ResourceEnum.COMPANY.getType(),
         ResourceEnum.DEPOT.getType(), ResourceEnum.DEPARTMENT.getType(), ResourceEnum.BRANCH.getType() };
      setPermission(res, baseInfoChildren, pset, baseInfo);

      Permission orderForm = pset.get(ResourceEnum.ORDERFORM);
      if (orderForm != null && orderForm.getOperator() != 0) {
        res.add(orderForm);
      }

      int[] manageChildren = { ResourceEnum.ROLE.getType(),
         ResourceEnum.EMPLOYEE.getType(),
         ResourceEnum.ACCOUNT.getType() };
      setPermission(res, manageChildren, pset, manage);

      int[] statisticChildren = { ResourceEnum.LOG.getType(), ResourceEnum.REVENUE.getType() };
      setPermission(res, statisticChildren, pset, statistic);
    }

    return res;
  }

  private void setPermission(List<Permission> res, int[] names,
                             Map<Integer, Permission> pset,
                             Permission fperm)
  {
    for (Integer name : names) {
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

  private void mergePermission(Permission permission,
                               Map<Integer, Permission> value)
  {
    Permission p = value.get(permission.getResource());

    if (p == null) {
      value.put(permission.getResource(), permission);
    } else {
      p.setOperator(p.getOperator() | permission.getOperator());
    }
  }

  private void setAdminPermissions(List<Permission> res) {
    int[] names = { ResourceEnum.PRODUCT.getType(), ResourceEnum.UNIT.getType(),
       ResourceEnum.PRICE.getType(), ResourceEnum.COMPANY.getType(),
       ResourceEnum.DEPOT.getType(), ResourceEnum.DEPARTMENT.getType(),
       ResourceEnum.BRANCH.getType(), ResourceEnum.ORDERFORM.getType(),
       ResourceEnum.ROLE.getType(),
       ResourceEnum.EMPLOYEE.getType(), ResourceEnum.ACCOUNT.getType(),
       ResourceEnum.LOG.getType(), ResourceEnum.REVENUE.getType() };

    for (Integer name : names) {
      Permission nperm = new Permission();
      nperm.setResource(name);
      nperm.setOperator(PermissionEnum.ADMIN.getPermission());
      res.add(nperm);
    }
  }

  private final EmployeeService employeeService;
}

package com.pssdev.pss.config;

import com.pssdev.pss.entity.*;
import com.pssdev.pss.service.EmployeeService;
import com.pssdev.pss.util.Tool;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PssRealm extends AuthorizingRealm {

  private final EmployeeService employeeService;

  @Autowired
  @Lazy // dependencies service must lazy loading for realm initialize.
  public PssRealm(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }

  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    // 获取用户名
    String username = (String) principals.getPrimaryPrincipal();

    Employee emp = employeeService.getEmployeeByName(username);

    SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
    // 给该用户设置角色
    Set<Role> roles = emp.getRoles();

    for(Role role : roles) {
      // 添加角色
      authorizationInfo.addRole(role.getName());
      // 添加权限
      for(Permission permission: role.getPermissions()) {
        authorizationInfo.addStringPermission(Tool.buildPermissionString(permission));
      }
    }

    return authorizationInfo;
  }

  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
    String username = (String) token.getPrincipal();

    Employee emp = employeeService.getEmployeeByName(username);

    if(emp == null) {
      return null;
    }

    AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(emp.getName(), emp.getPassword(), getName());

    return authcInfo;
  }
}

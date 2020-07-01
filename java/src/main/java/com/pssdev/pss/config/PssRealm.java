package com.pssdev.pss.config;

import com.pssdev.pss.entity.*;
import com.pssdev.pss.service.EmployeeService;
import com.pssdev.pss.util.Tool;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class PssRealm extends AuthorizingRealm {

  private final EmployeeService employeeService;

  @Autowired
  @Lazy // dependencies service must lazy loading for realm initialize.
  public PssRealm(EmployeeService employeeService, CredentialsMatcher credentialsMatcher) {
    super(credentialsMatcher);
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
    String userName = (String) token.getPrincipal();

    Employee emp = employeeService.getEmployeeByName(userName);

    if(emp == null) {
      return null;
    }

    ByteSource credentialsSalt = ByteSource.Util.bytes(userName);

    AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(userName,
       emp.getPassword(), credentialsSalt, getName());

    return authcInfo;
  }
}

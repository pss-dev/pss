package com.pssdev.pss.config;

import com.pssdev.pss.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class PssRealm extends AuthorizingRealm {
   @Override
   protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
      // 获取用户名
      String username = (String) principals.getPrimaryPrincipal();
      SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
      // 给该用户设置角色
      authorizationInfo.setRoles(null);
      // 给该用户设置权限
      authorizationInfo.setStringPermissions(null);

      return authorizationInfo;
   }

   @Override
   protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
      String username = (String) token.getPrincipal();

      User user = null; // Todo get user
      if(user != null) {
         SecurityUtils.getSubject().getSession().setAttribute("user", user);
         AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.getName(), user.getPassword(), "myRealm");
         return authcInfo;
      } else {
         return null;
      }
   }
}

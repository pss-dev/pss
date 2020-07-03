package com.pssdev.pss.config;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;

public class PssWildcardPermissionResolver extends WildcardPermissionResolver {

   @Override
   public Permission resolvePermission(String permissionString) {
      return new PssWildcardPermission(permissionString, isCaseSensitive());
   }
}

package com.pssdev.pss.config;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.WildcardPermission;

import java.util.List;
import java.util.Set;

public class PssWildcardPermission extends WildcardPermission {
   public PssWildcardPermission() {
      super();
   }

   public PssWildcardPermission(String wildcardString) {
      super(wildcardString);
   }

   public PssWildcardPermission(String wildcardString, boolean caseSensitive) {
      super(wildcardString, caseSensitive);
   }

   @Override
   public boolean implies(Permission p) {
      // By default only supports comparisons with other WildcardPermissions
      if (!(p instanceof PssWildcardPermission)) {
         return false;
      }

      PssWildcardPermission wp = (PssWildcardPermission) p;

      List<Set<String>> otherParts = wp.getParts();

      int i = 0;
      for (Set<String> otherPart : otherParts) {
         // If this permission has less parts than the other permission, everything after the number of parts contained
         // in this permission is automatically implied, so return true
         if (getParts().size() - 1 < i) {
            return true;
         } else {
            Set<String> part = getParts().get(i);
            if (!part.contains(WILDCARD_TOKEN) && !part.containsAll(otherPart)
               // 匹配注解上的通配符: 如果注解上使用 <code>WILDCARD_TOKEN</code>
               // 代表任意权限均可匹配
               && !otherPart.contains(WILDCARD_TOKEN))
            {
               return false;
            }
            i++;
         }
      }

      // If this permission has more parts than the other parts, only imply it if all of the other parts are wildcards
      for (; i < getParts().size(); i++) {
         Set<String> part = getParts().get(i);
         if (!part.contains(WILDCARD_TOKEN)) {
            return false;
         }
      }

      return true;
   }
}

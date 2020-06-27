package com.pssdev.pss.util;

import com.pssdev.pss.entity.Permission;

public final class Tool {

   public static final String ALL_PERMISSION = "*";
   public static final String PART_DIVIDER_TOKEN = ":";
   public static final String SUBPART_DIVIDER_TOKEN = ",";

   public static String buildPermissionString(Permission permission) {
      StringBuilder sb = new StringBuilder();
      sb.append(permission.getResource());
      Integer operator = permission.getOperator();
      PermissionEnum p = PermissionEnum.valueOf(operator);

      sb.append(PART_DIVIDER_TOKEN);

      sb.append(getOperatorPermission(p));

      return sb.toString();
   }

   public static String getOperatorPermission(PermissionEnum permission) {
      switch(permission) {
         case READ:
            return "r";
         case WRITE:
            return "w";
         case DELETE:
            return "d";
         case VERIFY:
            return "v";
         case ADMIN:
            return ALL_PERMISSION;
         default:
            return "";
      }


   }
}

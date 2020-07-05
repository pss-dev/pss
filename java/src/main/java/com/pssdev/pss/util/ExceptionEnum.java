package com.pssdev.pss.util;

public enum ExceptionEnum {

   /**
    * Login Exceptions.
    */
   SECURITY_UNKNOWN_ACCOUNT("00001", "账户不存在!"),
   SECURITY_INCORRECT_CREDENTIALS("00002", "密码不正确!"),
   SECURITY_LOCKED_ACCOUNT("00003", "账户被锁定!"),
   SECURITY_EXCESSIVE_ATTEMPTS("00004", "尝试次数太多, 请稍后重试!"),
   SECURITY_AUTHENTICATION("00005", "登录失败, 请稍后再试!");

   /**
    * 错误代码
    */
   private String code;

   /**
    * 用于提示用户的信息
    */
   private String message;

   ExceptionEnum(String code, String message) {
      this.code = code;
      this.message = message;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }
}

package com.pssdev.pss.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SecurityController {

   @GetMapping("/login")
   public String gotoLoginPage() {
      return "login";
   }

   @PostMapping("/api/1.0/login")
   public String login(@RequestParam String userName,
                       @RequestParam String password,
                       @RequestParam(required = false) boolean rememberMe)
   {
      Subject currentUser = SecurityUtils.getSubject();

      // 如果用户没有登录
      if(!currentUser.isAuthenticated()) {
         // 封装用户和密码为 UsernamePasswordToken
         UsernamePasswordToken token = new UsernamePasswordToken(userName, password);

         // 设置记住我, 在整个会话中记住身份.
         token.setRememberMe(rememberMe);

         try {
            // 执行登录
            currentUser.login(token);
         }
         catch(UnknownAccountException uae) {
            System.out.println("==用户不存在==" + uae.getMessage());
            throw uae;
         }
         catch(IncorrectCredentialsException ice) {
            System.out.println("====密码不正确====" + ice.getMessage());
            throw ice;
         }
         catch(LockedAccountException lae) {
            System.out.println("===账户被锁定==" + lae.getMessage());
            throw lae;
         }
         catch(ExcessiveAttemptsException eae) {
            System.out.println("=====尝试次数太多===" + eae.getMessage());
            throw eae;
         }
         catch(AuthenticationException e) {
            System.out.println("登录失败: " + token.getPrincipal() + ", " + e.getMessage() + "===" + e.getClass().getSimpleName());
            throw e;
         }
      }

      // 登录成功跳转到 / 页面
      return "redirect:/";
   }

   @GetMapping("/api/1.0/logout")
   public String logout() {
      SecurityUtils.getSubject().logout();

      return "redirect:/login";
   }
}

package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Employee;
import com.pssdev.pss.service.EmployeeService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
         catch(AuthenticationException e) {
            throw e;
         }
      }

      // 登录成功跳转到 / 页面
      return "redirect:/";
   }

   @GetMapping("/api/1.0/principal")
   @ResponseBody
   public Employee logout() {
      Subject subject = SecurityUtils.getSubject();
      Object principal = subject.getPrincipal();
      Employee employee = null;

      if(principal != null) {
         employee = employeeService.getEmployeeByName(principal.toString());
      }

      return employee;
   }

   @GetMapping("/error")
   public String gotoErrorPage() {
      return "/error/exception";
   }

   private final EmployeeService employeeService;
}

package com.pssdev.pss.controller;

import com.pssdev.pss.entity.User;
import com.pssdev.pss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {
   @Autowired
   private UserService userService;

   @GetMapping("user/getAll")
   @ResponseBody
   public List<User> getAllUsers() {
      return userService.getUsers();
   }

   @PutMapping("user/add")
   @ResponseBody
   public void addUser(User user) {
      userService.insertUser(user);
   }
}

package com.pssdev.pss.service;

import com.pssdev.pss.entity.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleServiceTests {

   @Autowired
   private RoleService roleService;

   @Test
   public void test() {
      Role role = roleService.getRole(1);

      System.out.println(role);
   }
}

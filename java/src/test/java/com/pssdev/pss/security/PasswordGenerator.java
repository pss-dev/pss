package com.pssdev.pss.security;

import com.pssdev.pss.util.Tool;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Just generator password.")
public class PasswordGenerator {

   @Test
   public void passwordGenerator() {
      String userName = "admin";
      String pwd = "admin";
      System.out.println(Tool.generatorPassword(userName, pwd));
      // df655ad8d3229f3269fad2a8bab59b6c
   }
}

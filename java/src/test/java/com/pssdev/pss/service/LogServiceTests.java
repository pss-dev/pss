package com.pssdev.pss.service;

import com.pssdev.pss.entity.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class LogServiceTests {

   @Autowired
   private LogService logService;

   @Test
   public void testGetAllLogs() {
      List<Log> logs = logService.getAllLogs();

      System.out.println(logs);
   }
}

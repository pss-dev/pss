package com.pssdev.pss.controller;

import com.pssdev.pss.service.LogService;
import com.pssdev.pss.entity.Log;
import com.pssdev.pss.model.LogSearchModel;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0")
public class LogController {
  @Autowired
  private LogService logService;

  @PostMapping("/log")
  @RequiresPermissions("*:16384:r")
  public List<Log> getLogs(@RequestBody LogSearchModel searchModel) {
    return logService.getLogs(searchModel);
  }
}
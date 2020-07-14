package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Log;
import com.pssdev.pss.model.LogSearchModel;
import com.pssdev.pss.service.ExportService;
import com.pssdev.pss.service.LogService;
import com.pssdev.pss.util.Tool;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/1.0")
public class LogController {

  @Autowired
  public LogController(LogService logService) {
    this.logService = logService;
  }

  @PostMapping("/log")
  @RequiresPermissions("*:16384:r")
  public List<Log> getLogs(@RequestBody LogSearchModel searchModel) {
    return logService.getLogs(searchModel);
  }

  @GetMapping("/log/export")
  public void export(HttpServletResponse response) throws Exception {
    ServletOutputStream out = response.getOutputStream();

    logService.export(out);

    LocalDateTime localDateTime = LocalDateTime.now();
    String fileName = "Pss MS Audit-"
       + localDateTime.format(DateTimeFormatter.ofPattern(Tool.DEFAULT_DATETIME_FORMAT))
       + ExportService.Excel2007_Suffix;

    String type = new MimetypesFileTypeMap().getContentType(fileName);

    response.setCharacterEncoding("utf-8");
    response.setContentType(type);
    String downloadFileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
    response.setHeader("Content-Disposition", "attachment;fileName=" + downloadFileName);
    out.flush();
  }

  private final LogService logService;
}

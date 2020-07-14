package com.pssdev.pss.service;

import com.pssdev.pss.entity.Log;
import com.pssdev.pss.model.LogSearchModel;

import java.util.List;

public interface LogService extends ExportService {
  void insertLog(Log log) throws Exception;

  void deleteLog(Log log);

  void modifyLog(Log log) throws Exception;

  Log getLog(Integer id);

  List<Log> getLogs(LogSearchModel searchModel);

  List<Log> getAllLogs();
}

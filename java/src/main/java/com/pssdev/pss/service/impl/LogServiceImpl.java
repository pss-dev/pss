
package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.LogDao;
import com.pssdev.pss.entity.Log;
import com.pssdev.pss.service.LogService;
import com.pssdev.pss.model.LogSearchModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("logService")
public class LogServiceImpl implements LogService {

  @Autowired
  public LogServiceImpl(LogDao logDao) {
    this.logDao = logDao;
  }

  @Transactional
  @Override
  public void insertLog(Log log) throws Exception {
    logDao.insert(log);
  }

  @Transactional
  @Override
  public void deleteLog(Log log) {
    logDao.delete(log);
  }

  @Transactional
  @Override
  public void modifyLog(Log log) throws Exception {
    logDao.update(log);
  }

  @Transactional(readOnly = true)
  @Override
  public Log getLog(Integer id) {
    return logDao.get(id);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Log> getLogs(LogSearchModel searchModel) {
    return logDao.search(searchModel);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Log> getAllLogs() {
    return logDao.getAll();
  }

  private final LogDao logDao;
}


package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.LogDao;
import com.pssdev.pss.entity.Log;
import com.pssdev.pss.model.LogSearchModel;
import com.pssdev.pss.service.LogService;
import com.pssdev.pss.util.ExportUtil;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.OutputStream;
import java.util.List;

import static com.pssdev.pss.util.ExportUtil.DEFAULT_COL_WIDTH;

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

  @Transactional(readOnly = true)
  public void export(OutputStream out, LogSearchModel filter) throws Exception {
    int columnCount = Log.exportColumnCount();

    HSSFWorkbook workbook = HSSFWorkbookFactory.createWorkbook();
    HSSFSheet sheet = workbook.createSheet("Pss MS Audit");

    for(int i = 0; i < columnCount; i++) {
      sheet.setColumnWidth(i, DEFAULT_COL_WIDTH);
    }

    HSSFCreationHelper helper = workbook.getCreationHelper();

    // create title
    ExportUtil.createTitleRow(workbook, sheet, "Pss MS Audit", columnCount);

    // create header
    HSSFCell cell;
    int colIndex = 0;
    int rowIndex = 1; // title row
    HSSFRow header = ExportUtil.createRow(workbook, sheet, rowIndex++);

    for(int i = 0; i< columnCount; i++) {
      cell = ExportUtil.createHeaderCell(workbook, header, colIndex);
      cell.setCellValue(Log.exportLabel(colIndex));
      colIndex++;
    }

    // fill data
    List<Log> logs = logDao.search(filter);
    HSSFRow dataRow;

    for(Log log : logs) {
      colIndex = 0;
      dataRow = ExportUtil.createRow(workbook, sheet, rowIndex++);

      for(int i = 0; i < columnCount; i++) {
        cell = ExportUtil.createCell(workbook, dataRow, colIndex);
        ExportUtil.fillData(helper, cell, Log.exportData(log, colIndex));
        colIndex++;
      }
    }

    workbook.write(out);
  }

  private final LogDao logDao;
}

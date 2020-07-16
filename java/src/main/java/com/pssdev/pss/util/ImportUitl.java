package com.pssdev.pss.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportUitl {
  public static Workbook getWorkBook(InputStream in, String fileName) throws IOException {
    Workbook workbook = null;

    if (fileName.endsWith(Excel2007_Suffix)) {
      workbook = new XSSFWorkbook();
    } else if (fileName.endsWith(Excel_xls)) {
      workbook = new HSSFWorkbook();
    }

    return workbook;
  }

  public static boolean isColumnCountError(int count) {
    return count != columnCount;
  }

  public static int columnCount = 18;

  public static String Excel_xls = ".xls";
  public static String Excel2007_Suffix = ".xlsx";
}
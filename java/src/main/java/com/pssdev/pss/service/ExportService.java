package com.pssdev.pss.service;

import java.io.OutputStream;

public interface ExportService <T> {

   String Excel2007_Suffix = ".xlsx";
   String Excel_xls = ".xls";

   void export(OutputStream out, T filter) throws Exception;
}

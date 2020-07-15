package com.pssdev.pss.service;

import java.io.OutputStream;

public interface ExportService <T> {

   String Excel2007_Suffix = ".xlsx";

   void export(OutputStream out, T filter) throws Exception;
}

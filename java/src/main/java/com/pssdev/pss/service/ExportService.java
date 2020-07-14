package com.pssdev.pss.service;

import java.io.OutputStream;

public interface ExportService {

   String Excel2007_Suffix = ".xlsx";

   void export(OutputStream out) throws Exception;
}

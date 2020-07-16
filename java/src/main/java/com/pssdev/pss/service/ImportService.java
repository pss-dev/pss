package com.pssdev.pss.service;

import org.springframework.web.multipart.MultipartFile;

public interface ImportService<T> {
  void importData(MultipartFile file, String parentId) throws Exception;
}

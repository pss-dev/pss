package com.pssdev.pss.dao;

import com.pssdev.pss.entity.Log;
import com.pssdev.pss.model.LogSearchModel;

import java.util.List;

public interface LogDao extends CURDDao<Log, Integer> {

  /**
   * 查询单据
   */
  List<Log> search(LogSearchModel model);
}

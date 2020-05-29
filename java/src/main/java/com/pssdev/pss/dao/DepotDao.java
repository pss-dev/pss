package com.pssdev.pss.dao;

import com.pssdev.pss.entity.Depot;

import java.util.List;

public interface DepotDao extends CURDDao<Depot, Integer> {

  /**
   * 获得所有仓库信息
   *
   * @return
   * @param fatherId
   */
  List<Depot> getDepots(Integer fatherId);
}

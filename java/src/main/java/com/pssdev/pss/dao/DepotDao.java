package com.pssdev.pss.dao;

import com.pssdev.pss.entity.Depot;

import java.util.List;

public interface DepotDao {
  /**
   * 插入仓库信息
   * 
   * @param Depot
   */
  void insertDepot(Depot depot);

  /**
   * 删除仓库信息
   * 
   * @param id
   */
  void deleteDepot(Integer id);

  /**
   * 删除仓库信息
   * 
   * @param Depot
   */
  void deleteDepot(Depot depot);

  /**
   * 更新仓库信息
   * 
   * @param Depot
   */
  void modifyDepot(Depot depot);

  /**
   * 根据id获取仓库信息
   * 
   * @param id
   */
  Depot getDepot(Integer id);

  /**
   * 获得所有仓库信息
   * 
   * @return
   */
  List<Depot> getDepots();

  /**
   * 获得所有仓库信息
   * 
   * @return
   * @param fatherId
   */
  List<Depot> getDepots(Integer fatherId);
}

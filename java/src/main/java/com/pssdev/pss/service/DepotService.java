package com.pssdev.pss.service;

import com.pssdev.pss.entity.Depot;

import java.util.List;

public interface DepotService {
  /**
   * get Depot by id
   *
   * @param parentId if null get all dept, else get specified dept.
   */
  List<Depot> getDepots(Integer parentId);

  /**
   * get all Depots
   */
  List<Depot> getDepots();

  /**
   * insert a Depot
   *
   * @return new Depot id.
   */
  void insertDepot(Depot depot) throws Exception;

  /**
   * update Depot
   */
  void updateDepot(Depot depot) throws Exception;

  /**
   * delete a Depot
   */
  void deleteDepot(Depot depot);
}

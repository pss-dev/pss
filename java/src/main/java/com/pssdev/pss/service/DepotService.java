package com.pssdev.pss.service;

import com.pssdev.pss.entity.Depot;
import com.pssdev.pss.entity.DepotItem;

import java.util.List;

public interface DepotService {
  /**
   * get Depot by id
   *
   * @param parentId if null get all dept, else get specified dept.
   */
  List<Depot> getDepots(Integer parentId, Integer branchID);

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

  /**
   * get inventory Product
   * 
   * @param depotId
   * @param productId
   * @return
   */
  public DepotItem inventoryProduct(int depotId, int productId);

  /**
   * get inventory Product
   * 
   * @param depotId
   * @return
   */
  public List<DepotItem> inventoryProducts(int depotId, Integer productFatherID);

  /**
   * putIn Products
   * 
   * @param items
   * @return
   */
  public void putInProducts(List<DepotItem> items);
}

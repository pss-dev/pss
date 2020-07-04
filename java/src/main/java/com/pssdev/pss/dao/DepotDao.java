package com.pssdev.pss.dao;

import com.pssdev.pss.entity.Depot;
import com.pssdev.pss.entity.DepotItem;
import com.pssdev.pss.entity.Product;

import java.util.List;

public interface DepotDao extends CURDDao<Depot, Integer> {

  /**
   * 获得所有仓库信息
   *
   * @return
   * @param fatherId
   */
  List<Depot> getDepots(Integer fatherId);

  /**
   * 入库
   * @param items
   */
  void putInProducts(List<DepotItem> items);

  /**
   * 出库
   * @param items
   */
  void removeProducts(List<DepotItem> items);

  /**
   * 获得仓库所有商品
   * @param depot 仓库
   * @return
   */
  List<DepotItem> getInventoryProducts(Depot depot);

  /**
   * 获得仓库特定商品
   * @param depot 仓库
   * @param product 商品
   * @return
   */
  DepotItem inventoryProduct(Depot depot, Product product);
}

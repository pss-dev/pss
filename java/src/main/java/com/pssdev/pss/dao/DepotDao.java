package com.pssdev.pss.dao;

import com.pssdev.pss.entity.*;

import java.util.List;

public interface DepotDao extends CURDDao<Depot, Integer> {

  /**
   * 获得所有仓库信息
   *
   * @return
   * @param fatherId
   */
  List<Depot> getDepots(Integer fatherId, Integer branchId);

  /**
   * 入库
   * 
   * @param items
   */
  void putInProducts(List<DepotItem> items);

  /**
   * 出库
   * 
   * @param items
   */
  void putOutProducts(List<DepotItem> items);

  /**
   * 获得仓库所有商品
   * 
   * @param depot 仓库
   * @return
   */
  List<DepotItem> getInventoryProducts(Depot depot, Integer productFatherID);

  /**
   * 获得仓库特定商品
   * 
   * @param depot   仓库
   * @param product 商品
   * @return
   */
  DepotItem inventoryProduct(Depot depot, Product product);

  List<DepotItem> getItemsByOrderForm(OrderForm orderForm);
}

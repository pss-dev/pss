package com.pssdev.pss.dao;

import com.pssdev.pss.entity.ProductUnit;

import java.util.List;

public interface ProductUnitDao extends CURDDao<ProductUnit, Integer> {
  ProductUnit getByName(String name);

  /**
   * 获得所有商品单位信息
   * 
   * @return
   * @param fatherId
   */
  List<ProductUnit> getProductUnits(Integer fatherId);
}

package com.pssdev.pss.dao;

import com.pssdev.pss.entity.Product;

import java.util.List;

public interface ProductDao extends CURDDao<Product, Integer> {
  Product getByName(String name);

  /**
   * 获得所有商品信息
   * 
   * @return
   * @param fatherId
   */
  List<Product> getProducts(Integer fatherId);
}

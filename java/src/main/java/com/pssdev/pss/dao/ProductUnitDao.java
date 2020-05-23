package com.pssdev.pss.dao;

import com.pssdev.pss.entity.ProductUnit;

import java.util.List;

public interface ProductUnitDao {
   /**
    * 插入商品单位信息
    * @param productUnit
    */
   void insertProductUnit(ProductUnit productUnit);

   /**
    * 删除商品单位信息
    * @param id
    */
   void deleteProductUnit(Integer id);

   /**
    * 删除商品单位信息
    * @param productUnit
    */
   void deleteProductUnit(ProductUnit productUnit);

   /**
    * 更新商品单位信息
    * @param productUnit
    */
   void modifyProductUnit(ProductUnit productUnit);

   /**
    * 根据id获取商品单位信息
    * @param id
    */
   ProductUnit getProductUnit(Integer id);

   /**
    * 获得所有商品单位信息
    * @return
    */
   List getProductUnits();

   /**
    * 获得所有商品单位信息
    * @return
    * @param fatherId
    */
   List<ProductUnit> getProductUnits(Integer fatherId);
}

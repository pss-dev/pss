package com.pssdev.pss.service;

import com.pssdev.pss.entity.ProductUnit;

import java.util.List;

public interface ProductUnitService {
   /**
    * 插入商品单位信息
    * @param productUnit
    */
   void insertProductUnit(ProductUnit productUnit) throws Exception;

   /**
    * 删除商品单位信息
    * @param productUnit
    */
   void deleteProductUnit(ProductUnit productUnit);

   /**
    * 修改商品单位信息
    * @param productUnit
    */
   void modifyProductUnit(ProductUnit productUnit) throws Exception;

   /**
    * 获得所有用户信息
    * @return
    * @param fatherId
    */
   List<ProductUnit> getProductUnits(Integer fatherId);

   /**
    * 获得所有商品单位信息
    * @return
    */
   List<ProductUnit> getProductUnits();
}

package com.pssdev.pss.dao;

import com.pssdev.pss.entity.Price;

import java.util.List;

public interface PriceDao {
   /**
    * 插入价格信息
    * @param price
    */
   void insertPrice(Price price);

   /**
    * 删除价格信息
    * @param id
    */
   void deletePrice(Integer id);

   /**
    * 删除价格信息
    * @param price
    */
   void deletePrice(Price price);

   /**
    * 更新价格信息
    * @param price
    */
   void modifyPrice(Price price);

   /**
    * 根据id获取价格信息
    * @param id
    */
   Price getPrice(Integer id);

   /**
    * 获得所有价格信息
    * @return
    */
   List<Price> getPrices();
}

package com.pssdev.pss.service;

import com.pssdev.pss.entity.Price;

import java.util.List;

public interface PriceService {
   /**
    * 插入价格名称信息
    * @param price
    */
   void insertPrice(Price price) throws Exception;

   /**
    * 删除价格名称信息
    * @param price
    */
   void deletePrice(Price price);

   /**
    * 修改价格名称信息
    * @param price
    */
   void modifyPrice(Price price) throws Exception;

   /**
    * 获得所有价格名称信息
    * @return
    */
   List<Price> getPrices();
}

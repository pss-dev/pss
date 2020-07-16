package com.pssdev.pss.service;

import com.pssdev.pss.entity.Price;

import java.util.List;

public interface PriceService {

  /**
   * 修改价格名称信息
   * 
   * @param price
   */
  void modifyPrice(Price price) throws Exception;

  /**
   * 获得所有价格名称信息
   * 
   * @return
   */
  List<Price> getPrices();
}

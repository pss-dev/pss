package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.PriceDao;
import com.pssdev.pss.entity.Price;
import org.springframework.stereotype.Repository;

@Repository("priceDao")
public class PriceDaoImpl extends BaseDao<Price, Integer> implements PriceDao {

   @Override
   protected Class<Price> getClazz() {
      return Price.class;
   }
}

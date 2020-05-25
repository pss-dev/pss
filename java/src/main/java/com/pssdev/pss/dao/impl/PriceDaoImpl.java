package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.PriceDao;
import com.pssdev.pss.entity.Price;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PriceDaoImpl extends BaseDao implements PriceDao {
   @Override
   public void insertPrice(Price price) {
      this.getSession().save(price);
   }

   @Override
   public void deletePrice(Integer id) {
      this.getSession()
              .createQuery("delete from Price p where p.id =" + id).executeUpdate();

   }

   @Override
   public void deletePrice(Price price) {
      this.getSession().delete(price);
   }

   @Override
   public void modifyPrice(Price price) {
      this.getSession().update(price);

   }

   @Override
   public Price getPrice(Integer id) {
      return getSession().get(Price.class, id);
   }

   @Override
   public List<Price> getPrices() {
      return getSession().createQuery("from Price").list();
   }
}

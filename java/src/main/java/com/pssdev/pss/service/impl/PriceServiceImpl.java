package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.PriceDao;
import com.pssdev.pss.entity.Price;
import com.pssdev.pss.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PriceServiceImpl implements PriceService {
   @Autowired
   private PriceDao priceDao;

   @Override
   public void insertPrice(Price price) throws Exception {
      this.priceDao.insert(price);
   }

   @Override
   public void deletePrice(Price price) {
      this.priceDao.delete(price);
   }

   @Override
   public void modifyPrice(Price price) throws Exception {
      Price old = this.priceDao.get(price.getId());

      if(old != null) {
         old.setLabel(price.getLabel());
         old.setName(price.getName());
         this.priceDao.update(old);
      }
      else {
         throw new Exception("价格名称不存在");
      }
   }

   @Override
   public List<Price> getPrices() {
      if(this.priceDao.getAll().size() < 10) {
         for(int i = 0; i < 10; i++) {
            Price price = new Price();
            price.setName("价格" + i);
            price.setLabel("价格" + i);
            try {
               this.priceDao.insert(price);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      }

      return this.priceDao.getAll();
   }
}

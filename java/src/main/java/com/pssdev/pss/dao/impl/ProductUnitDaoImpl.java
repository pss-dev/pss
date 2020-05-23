package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.ProductUnitDao;
import com.pssdev.pss.entity.ProductUnit;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductUnitDaoImpl extends BaseDao implements ProductUnitDao {
   @Override
   public void insertProductUnit(ProductUnit productUnit) {
      this.getSession().save(productUnit);
   }

   @Override
   public void deleteProductUnit(Integer id) {
      this.getSession()
              .createQuery("delete from ProductUnit p where p.id = " + id )
              .executeUpdate();
   }

   @Override
   public void deleteProductUnit(ProductUnit productUnit) {
      this.getSession().delete(productUnit);
   }

   @Override
   public void modifyProductUnit(ProductUnit productUnit) {
      this.getSession().update(productUnit);
   }

   @Override
   public ProductUnit getProductUnit(Integer id) {
      return getSession().get(ProductUnit.class, id);
   }

   @Override
   public List<ProductUnit> getProductUnits() {
      return getSession().createQuery("from ProductUnit").list();
   }

   @Override
   public List<ProductUnit> getProductUnits(Integer fatherId) {
      return getSession().createQuery("from ProductUnit p where p.fatherId" + fatherId).list();
   }
}

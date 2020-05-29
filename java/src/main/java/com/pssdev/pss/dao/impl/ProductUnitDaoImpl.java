package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.ProductUnitDao;
import com.pssdev.pss.entity.ProductUnit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productUnitDao")
public class ProductUnitDaoImpl extends BaseDao<ProductUnit, Integer> implements ProductUnitDao {

   @Override
   public List<ProductUnit> getProductUnits(Integer fatherId) {
      return getSession().createQuery("from ProductUnit p where p.fatherId" + fatherId).list();
   }

   @Override
   protected Class<ProductUnit> getClazz() {
      return ProductUnit.class;
   }
}

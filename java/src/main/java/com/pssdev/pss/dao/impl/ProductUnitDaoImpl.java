package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.ProductUnitDao;
import com.pssdev.pss.entity.ProductUnit;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("productUnitDao")
public class ProductUnitDaoImpl extends BaseDao<ProductUnit, Integer> implements ProductUnitDao {

   @Override
   public List<ProductUnit> getProductUnits(Integer fatherId) {
      String hql = "from ProductUnit p ";

      if(fatherId == -1) {
         hql += "where p.parent = null";
      }
      else {
         hql += "where p.parent.id = " + fatherId;
      }

      return getSession().createQuery(hql).list();
   }

   @Override
   protected Class<ProductUnit> getClazz() {
      return ProductUnit.class;
   }
}

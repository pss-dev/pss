package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.ProductDao;
import com.pssdev.pss.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productDao")
public class ProductDaoImpl extends BaseDao<Product, Integer> implements ProductDao {

   @Override
   public List<Product> getProducts(Integer fatherId) {
      return getSession()
              .createQuery("from Product p where p.fatherId" + fatherId).list();
   }

   @Override
   protected Class<Product> getClazz() {
      return Product.class;
   }
}

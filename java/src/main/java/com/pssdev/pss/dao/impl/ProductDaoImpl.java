package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.ProductDao;
import com.pssdev.pss.entity.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductDaoImpl extends BaseDao implements ProductDao {
   @Override
   public void insertProduct(Product product) {
      this.getSession().save(product);
   }

   @Override
   public void deleteProduct(Integer id) {
      this.getSession().createQuery("delete from Product p where p.id = " + id).executeUpdate();
   }

   @Override
   public void deleteProduct(Product product) {
      this.getSession().delete(product);
   }

   @Override
   public void modifyProduct(Product product) {
      this.getSession().update(product);
   }

   @Override
   public Product getProduct(Integer id) {
      return getSession().get(Product.class, id);
   }

   @Override
   public List<Product> getProducts() {
      return getSession().createQuery("from Product").list();
   }

   @Override
   public List<Product> getProducts(Integer fatherId) {
      return getSession()
              .createQuery("from Product p where p.fatherId" + fatherId).list();
   }
}

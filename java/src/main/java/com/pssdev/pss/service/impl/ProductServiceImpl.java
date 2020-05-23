package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.ProductDao;
import com.pssdev.pss.entity.Product;
import com.pssdev.pss.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
   @Autowired
   private ProductDao productDao;

   @Override
   public void insertProduct(Product product) throws Exception {
      this.productDao.insertProduct(product);
   }

   @Override
   public void deleteProduct(Product product) {
      this.productDao.deleteProduct(product);
   }

   @Override
   public void modifyProduct(Product product) throws Exception {
      this.productDao.modifyProduct(product);
   }

   @Override
   public List<Product> getProducts(Integer fatherId) {
      if(!StringUtils.isEmpty(fatherId)) {
         Product father = this.productDao.getProduct(fatherId);

         if(father == null) {
            return this.productDao.getProducts(fatherId);
         }
      }

      return this.productDao.getProducts();
   }

   @Override
   public List<Product> getProducts() {
      return this.productDao.getProducts();
   }
}

package com.pssdev.pss.service;

import com.pssdev.pss.entity.Product;

import java.util.List;

public interface ProductService {
   /**
    * 插入商品信息
    * @param product
    */
   void insertProduct(Product product) throws Exception;

   /**
    * 删除商品信息
    * @param product
    */
   void deleteProduct(Product product);

   /**
    * 修改商品信息
    * @param product
    */
   void modifyProduct(Product product) throws Exception;

   /**
    * 获得所有用户信息
    * @return
    * @param fatherId
    */
   List<Product> getProducts(Integer fatherId);

   /**
    * 获得所有用户信息
    * @return
    */
   List<Product> getProducts();
}
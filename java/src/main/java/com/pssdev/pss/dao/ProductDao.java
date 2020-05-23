package com.pssdev.pss.dao;

import com.pssdev.pss.entity.Product;

import java.util.List;

public interface ProductDao {
   /**
    * 插入商品信息
    * @param product
    */
   void insertProduct(Product product);

   /**
    * 删除商品信息
    * @param id
    */
   void deleteProduct(Integer id);

   /**
    * 删除商品信息
    * @param product
    */
   void deleteProduct(Product product);

   /**
    * 更新商品信息
    * @param product
    */
   void modifyProduct(Product product);

   /**
    * 根据id获取商品信息
    * @param id
    */
   Product getProduct(Integer id);

   /**
    * 获得所有商品信息
    * @return
    */
   List<Product> getProducts();

   /**
    * 获得所有商品信息
    * @return
    * @param fatherId
    */
   List<Product> getProducts(Integer fatherId);
}

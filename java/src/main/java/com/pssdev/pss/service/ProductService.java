package com.pssdev.pss.service;

import com.pssdev.pss.entity.Product;
import com.pssdev.pss.model.GeneratePriceModel;

import java.util.List;
import java.io.OutputStream;

public interface ProductService extends ImportService {
  /**
   * 插入商品信息
   * 
   * @param product
   */
  Integer insertProduct(Product product) throws Exception;

  /**
   * 删除商品信息
   * 
   * @param product
   */
  void deleteProduct(Product product);

  /**
   * 修改商品信息
   * 
   * @param product
   */
  void modifyProduct(Product product) throws Exception;

  /**
   * 获得所有用户信息
   * 
   * @return
   * @param fatherId
   */
  List<Product> getProducts(Integer fatherId);

  /**
   * 获得所有用户信息
   * 
   * @return
   */
  List<Product> getProducts();

  Product getProduct(Integer productId);

  /**
   * get product by name
   *
   * @return
   */
  Product getProductByName(String name);

  /**
   * get product by name
   */
  void getDataTemplate(OutputStream out) throws Exception;

  void generatePrice(GeneratePriceModel model);
}

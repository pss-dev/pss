package com.pssdev.pss.model;

import com.pssdev.pss.entity.Product;

public class ProductStockModel {
  private Product product;
  private int stock;

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public int getStock() {
    return stock;
  }

  public void setStock(int stock) {
    this.stock = stock;
  }
}
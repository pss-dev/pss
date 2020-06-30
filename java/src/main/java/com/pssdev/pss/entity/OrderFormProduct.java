package com.pssdev.pss.entity;

import javax.persistence.*;

@Entity
public class OrderFormProduct {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @JoinColumn(name = "product_id")
  @ManyToOne(targetEntity = Product.class)
  private Product product;

  @JoinColumn(name = "productUnit_id")
  @ManyToOne(targetEntity = ProductUnit.class)
  private ProductUnit productUnit;

  private int count;
  private Long price;
  private Long amount;
  private String note;
  private int actionType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public ProductUnit getProductUnit() {
    return productUnit;
  }

  public void setProductUnit(ProductUnit productUnit) {
    this.productUnit = productUnit;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }

  public Long getAmount() {
    return amount;
  }

  public void setAmount(Long amount) {
    this.amount = amount;
  }

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public int getActionType() {
    return actionType;
  }

  public void setActionType(int actionType) {
    this.actionType = actionType;
  }
}
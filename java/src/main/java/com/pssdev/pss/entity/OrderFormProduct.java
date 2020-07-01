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
  private ProductUnit unit;

  private int count;
  private double price;
  private double amount;
  private String note;

  @Transient
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

  public ProductUnit getUnit() {
    return unit;
  }

  public void setUnit(ProductUnit unit) {
    this.unit = unit;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int count) {
    this.count = count;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
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
package com.pssdev.pss.model;

import com.pssdev.pss.entity.*;

public class GeneratePriceModel {
  private Price targetPrice;
  private Price originalPrice;
  private String calculate;
  private double multiple;
  private int decimal;
  private Product parentProduct;

  public Price getTargetPrice() {
    return targetPrice;
  }

  public void setTargetPrice(Price targetPrice) {
    this.targetPrice = targetPrice;
  }

  public Price getOriginalPrice() {
    return originalPrice;
  }

  public void setOriginalPrice(Price originalPrice) {
    this.originalPrice = originalPrice;
  }

  public String getCalculate() {
    return calculate;
  }

  public void setCalculate(String calculate) {
    this.calculate = calculate;
  }

  public double getMultiple() {
    return multiple;
  }

  public void setMultiple(double multiple) {
    this.multiple = multiple;
  }

  public int getDecimal() {
    return decimal;
  }

  public void setDecimal(int decimal) {
    this.decimal = decimal;
  }

  public Product getparentProduct() {
    return parentProduct;
  }

  public void setparentProduct(Product parentProduct) {
    this.parentProduct = parentProduct;
  }
}

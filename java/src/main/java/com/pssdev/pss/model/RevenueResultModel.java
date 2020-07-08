package com.pssdev.pss.model;

public class RevenueResultModel {
  public RevenueResultModel(int purchaseCount, int sellCount, int purchaseReturnCount, int sellReturnCount,
      double expenditure, double income, double profit) {
    this.purchaseCount = purchaseCount;
    this.sellCount = sellCount;
    this.purchaseReturnCount = purchaseReturnCount;
    this.sellReturnCount = sellReturnCount;
    this.expenditure = expenditure;
    this.income = income;
    this.profit = profit;
  }

  public int getPurchaseCount() {
    return purchaseCount;
  }

  public void SetPurchaseCount(int purchaseCount) {
    this.purchaseCount = purchaseCount;
  }

  public int getSellCount() {
    return sellCount;
  }

  public void setSellCount(int sellCount) {
    this.sellCount = sellCount;
  }

  public int getPurchaseReturnCount() {
    return purchaseReturnCount;
  }

  public void setPurchaseReturnCount(int purchaseReturnCount) {
    this.purchaseReturnCount = purchaseReturnCount;
  }

  public int getSellReturnCount() {
    return sellReturnCount;
  }

  public void setSellReturnCount(int sellReturnCount) {
    this.sellReturnCount = sellReturnCount;
  }

  public double getExpenditure() {
    return expenditure;
  }

  public void setExpenditure(double expenditure) {
    this.expenditure = expenditure;
  }

  public double getIncome() {
    return income;
  }

  public void setIncome(double income) {
    this.income = income;
  }

  public double getProfit() {
    return profit;
  }

  public void setProfit(double profit) {
    this.profit = profit;
  }

  private int purchaseCount;
  private int sellCount;
  private int purchaseReturnCount;
  private int sellReturnCount;
  private double expenditure;
  private double income;
  private double profit;
}
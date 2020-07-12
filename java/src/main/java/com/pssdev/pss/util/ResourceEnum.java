package com.pssdev.pss.util;

public enum ResourceEnum {
  BASEINFO(1, "Base Info"),
  PRODUCT(2, "Product"),
  UNIT(4, "Unit"),
  PRICE(8, "Price"),
  COMPANY(16, "Company"),
  DEPOT(32, "Depot"),
  DEPARTMENT(64, "Department"),
  BRANCH(128, "Branch"),
  ORDERFORM(256, "OrderForm"),
  MANAGE(512, "Manage"),
  ROLE(1024, "Role"),
  EMPLOYEE(2048, "Employee"),
  ACCOUNT(4096, "Account"),
  STATISTIC(8192, "Statistic"),
  LOG(16384, "Log"),
  REVENUE(32768, "Manage");

  private int type;
  private String label;

  ResourceEnum(int type, String label) {
    this.type = type;
    this.label = label;
  }

  public int getType() {
    return type;
  }

  public String getLabel() {
    return label;
  }
}

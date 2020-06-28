package com.pssdev.pss.model;

public class OrderFormSearchModel {
  private int orderFormType;
  private int orderFormStatus;
  private long startDate;
  private long endDate;
  private int branchID;
  private int companyID;
  private int employeeID;
  private int departmentID;
  private int depotID;
  private int accountID;

  public int getOrderFormType() {
    return orderFormType;
  }

  public void setOrderFormType(int orderFormType) {
    this.orderFormType = orderFormType;
  }

  public int getOrderFormStatus() {
    return orderFormStatus;
  }

  public void setOrderFormStatus(int orderFormStatus) {
    this.orderFormStatus = orderFormStatus;
  }

  public long getStartDate() {
    return startDate;
  }

  public void setStartDate(Long startDate) {
    this.startDate = startDate;
  }

  public long getEndDate() {
    return endDate;
  }

  public void setEndDate(long endDate) {
    this.endDate = endDate;
  }

  public int getBranchID() {
    return branchID;
  }

  public void setBranchID(int branchID) {
    this.branchID = branchID;
  }

  public int getCompanyID() {
    return companyID;
  }

  public void setCompanyID(int companyID) {
    this.companyID = companyID;
  }

  public int getEmployeeID() {
    return employeeID;
  }

  public void setEmployeeID(int employeeID) {
    this.employeeID = employeeID;
  }

  public int getDepartmentID() {
    return departmentID;
  }

  public void setDepartmentID(int departmentID) {
    this.departmentID = departmentID;
  }

  public int getDepotID() {
    return depotID;
  }

  public void setDepotID(int depotID) {
    this.depotID = depotID;
  }

  public int getAccountID() {
    return accountID;
  }

  public void setAccountID(int accountID) {
    this.accountID = accountID;
  }
}
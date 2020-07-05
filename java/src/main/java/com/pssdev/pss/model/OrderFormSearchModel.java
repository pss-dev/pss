package com.pssdev.pss.model;

import java.util.Date;

public class OrderFormSearchModel {
  private Integer orderFormType;
  private Integer orderFormStatus;
  private Date startDate;
  private Date endDate;
  private Integer branchID;
  private Integer companyID;
  private Integer employeeID;
  private Integer departmentID;
  private Integer depotID;
  private Integer accountID;

  public Integer getOrderFormType() {
    return orderFormType;
  }

  public void setOrderFormType(Integer orderFormType) {
    this.orderFormType = orderFormType;
  }

  public Integer getOrderFormStatus() {
    return orderFormStatus;
  }

  public void setOrderFormStatus(Integer orderFormStatus) {
    this.orderFormStatus = orderFormStatus;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Integer getBranchID() {
    return branchID;
  }

  public void setBranchID(Integer branchID) {
    this.branchID = branchID;
  }

  public Integer getCompanyID() {
    return companyID;
  }

  public void setCompanyID(Integer companyID) {
    this.companyID = companyID;
  }

  public Integer getEmployeeID() {
    return employeeID;
  }

  public void setEmployeeID(Integer employeeID) {
    this.employeeID = employeeID;
  }

  public Integer getDepartmentID() {
    return departmentID;
  }

  public void setDepartmentID(Integer departmentID) {
    this.departmentID = departmentID;
  }

  public Integer getDepotID() {
    return depotID;
  }

  public void setDepotID(Integer depotID) {
    this.depotID = depotID;
  }

  public Integer getAccountID() {
    return accountID;
  }

  public void setAccountID(Integer accountID) {
    this.accountID = accountID;
  }
}
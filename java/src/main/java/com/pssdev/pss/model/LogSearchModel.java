package com.pssdev.pss.model;

public class LogSearchModel {
  private long startDate;
  private long endDate;

  public LogSearchModel() {
  }

  public LogSearchModel(long startDate, long endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public long getStartDate() {
    return startDate;
  }

  public void setStartDate(long startDate) {
    this.startDate = startDate;
  }

  public long getEndDate() {
    return endDate;
  }

  public void setEndDate(long endDate) {
    this.endDate = endDate;
  }
}

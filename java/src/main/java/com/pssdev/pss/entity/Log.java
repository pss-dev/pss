package com.pssdev.pss.entity;

import javax.persistence.*;
import java.util.Date;

@Entity()
public class Log {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private Date date;
  private String employee;
  private String action;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getEmployee() {
    return employee;
  }

  public void setEmployee(String employee) {
    this.employee = employee;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }
}

package com.pssdev.pss.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Log {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private Date date;
  private String employee;
  private String action;
  private String resource;
  private String message;

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

  public String getResource() {
    return resource;
  }

  public void setResource(String resource) {
    this.resource = resource;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "Log{" +
       "id=" + id +
       ", date=" + date +
       ", employee='" + employee + '\'' +
       ", action='" + action + '\'' +
       ", resource='" + resource + '\'' +
       ", message='" + message + '\'' +
       '}';
  }

  public static int exportColumnCount() {
    return 6;
  }

  public static String exportLabel(int index) {
    switch(index) {
      case 0:
        return "编号"; // id
      case 1:
        return "资源"; // resource
      case 2:
        return "行为"; // action
      case 3:
        return "操作人"; // employee
      case 4:
        return "日期"; // date
      case 5:
        return "备注"; // message
      default:
        return null;
    }
  }

  public static Object exportData(Log log, int index) {
    switch(index) {
      case 0:
        return log.id; // id
      case 1:
        return log.resource; // resource
      case 2:
        return log.action; // action
      case 3:
        return log.employee; // employee
      case 4:
        return log.date; // date
      case 5:
        return log.message; // message
      default:
        return null;
    }
  }
}

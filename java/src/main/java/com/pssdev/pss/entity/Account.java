package com.pssdev.pss.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
  private String bank;
  private String cardholder;
  private String account;
  private Account parent;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCardholder() {
    return cardholder;
  }

  public void setCardholder(String cardholder) {
    this.cardholder = cardholder;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getBank() {
    return bank;
  }

  public void setBank(String bank) {
    this.bank = bank;
  }

  public Account getParent() {
    return parent;
  }

  public void setParent(Account parent) {
    this.parent = parent;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Account account = (Account) o;

    return Objects.equals(id, account.id) && Objects.equals(name, account.name) && Objects.equals(bank, account.bank)
        && Objects.equals(cardholder, account.cardholder) && Objects.equals(account, account.account)
        && Objects.equals(parent, account.parent);
  }

  @Override
  public String toString() {
    return "Company{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", bank='" + bank + '\'' + ", cardholder='"
        + cardholder + '\'' + ", account='" + account + '\'' + ", parent='" + parent.getName() + '}';
  }
}

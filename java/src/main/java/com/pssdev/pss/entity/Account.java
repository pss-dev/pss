package com.pssdev.pss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Objects;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties
public class Account {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
  private String bank;
  private String cardholder;
  private String account;
  @JoinColumn(name = "father_id")
  @ManyToOne(targetEntity = Account.class)
  private Account parent;

  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonIgnore
  private Set<Account> children = new HashSet<>();

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

  public Set<Account> getChildren() {
    return children;
  }

  public void setChildren(Set<Account> children) {
    this.children = children;
  }

  public boolean isHaveChildren() {
    return this.children == null || this.children.size() == 0 ? false : true;
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

package com.pssdev.pss.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Depot {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
  private Integer branch;
  private String initials;
  private Integer fatherId;

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

  public Integer getBranch() {
    return branch;
  }

  public void setBranch(Integer branch) {
    this.branch = branch;
  }

  public String getInitials() {
    return initials;
  }

  public void setInitials(String initials) {
    this.initials = initials;
  }

  public Integer getFatherId() {
    return fatherId;
  }

  public void setFatherId(Integer fatherId) {
    this.fatherId = fatherId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Depot depot = (Depot) o;

    return Objects.equals(id, depot.id) && Objects.equals(name, depot.name) && Objects.equals(initials, depot.initials)
        && Objects.equals(branch, depot.branch) && Objects.equals(fatherId, depot.fatherId);
  }

  @Override
  public String toString() {
    return "Company{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", initials='" + initials + '\'' + ", branch='"
        + branch + '\'' + ", fatherId='" + fatherId + '\'' + '}';
  }
}
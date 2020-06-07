package com.pssdev.pss.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Depot {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
  @JoinColumn(name = "branch_id")
  @ManyToOne(targetEntity = Branch.class)
  private Branch branch;
  private String initials;
  @JoinColumn(name = "father_id")
  @ManyToOne(targetEntity = Depot.class)
  private Depot parent;

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

  public Branch getBranch() {
    return branch;
  }

  public void setBranch(Branch branch) {
    this.branch = branch;
  }

  public String getInitials() {
    return initials;
  }

  public void setInitials(String initials) {
    this.initials = initials;
  }

  public Depot getParent() {
    return parent;
  }

  public void setParent(Depot parent) {
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

    Depot depot = (Depot) o;

    return Objects.equals(id, depot.id) && Objects.equals(name, depot.name) && Objects.equals(initials, depot.initials)
        && Objects.equals(branch, depot.branch) && Objects.equals(parent, depot.parent);
  }

  @Override
  public String toString() {
    return "Company{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", initials='" + initials + '\'' + ", branch='"
        + branch + '\'' + ", parent='" + parent.getName() + '\'' + '}';
  }
}

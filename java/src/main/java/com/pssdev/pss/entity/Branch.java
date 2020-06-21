package com.pssdev.pss.entity;

import io.swagger.annotations.ApiModel;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "t_branch")
@ApiModel("分支信息")
public class Branch implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  @JoinColumn(name = "customer_id")
  @OneToOne(targetEntity = Company.class)
  private Company customer;
  @JoinColumn(name = "supplier_id")
  @OneToOne(targetEntity = Company.class)
  private Company supplier;
  private String initials;
  @JoinColumn(name = "parent_id")
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<Branch> children = new HashSet<>();
  @ManyToOne
  @JoinColumn(name = "parent_id")
  private Branch parent;

  public Branch() {
  }

  public Branch(Integer id) {
    this.id = id;
  }

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

  public Company getCustomer() {
    return customer;
  }

  public void setCustomer(Company customer) {
    this.customer = customer;
  }

  public Company getSupplier() {
    return supplier;
  }

  public void setSupplier(Company supplier) {
    this.supplier = supplier;
  }

  public String getInitials() {
    return initials;
  }

  public void setInitials(String initials) {
    this.initials = initials;
  }

  public void setChildren(Set<Branch> children) {
    this.children = children;
  }

  public Set<Branch> getChildren() {
    return children;
  }

  public void setParent(Branch parent) {
    this.parent = parent;
  }

  public Branch getParent() {
    return parent;
  }

  @Override
  public String toString() {
    return "Branch{" + "id=" + id + ", name='" + name + '\'' + ", customer='" + customer.getName() + '\''
        + ", supplier='" + supplier.getName() + '\'' + ", initials='" + initials + '\'' + ", parent="
        + (parent != null ? parent.id : null) + ", children=["
        + (children != null ? children.stream().map(d -> d.id + "").collect(Collectors.joining(",")) : null) + "]}";
  }
}

package com.pssdev.pss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "t_branch")
@ApiModel("分支信息")
@JsonIgnoreProperties
public class Branch implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;

  @Nullable
  @ManyToOne(targetEntity = Company.class)
  private Company customer;

  @Nullable
  @ManyToOne(targetEntity = Company.class)
  private Company supplier;
  private String initials;

  @ManyToOne
  @JoinColumn(name = "father_id")
  private Branch parent;

  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonIgnore
  private Set<Branch> children;

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

  public boolean isHaveChildren() {
    return this.children == null || this.children.size() == 0 ? false : true;
  }

  @Override
  public String toString() {
    return "Branch{" + "id=" + id + ", name='" + name + '\'' + ", initials='" + initials + '\'' + ", parent="
        + (parent != null ? parent.id : null) + ", children=["
        + (children != null ? children.stream().map(d -> d.id + "").collect(Collectors.joining(",")) : null) + "]}";
  }
}

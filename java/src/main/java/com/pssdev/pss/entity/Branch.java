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
  @ManyToMany(targetEntity = Company.class)
  private Set<Company> customers;

  @Nullable
  @ManyToMany(targetEntity = Company.class)
  private Set<Company> suppliers;
  private String initials;

  @ManyToOne
  @JoinColumn(name = "father_id")
  private Branch parent;

  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonIgnore
  private Set<Branch> children = new HashSet<>();

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

  public Set<Company> getCustomers() {
    return customers;
  }

  public void setCustomers(Set<Company> customers) {
    this.customers = customers;
  }

  public Set<Company> getSuppliers() {
    return suppliers;
  }

  public void setSuppliers(Set<Company> suppliers) {
    this.suppliers = suppliers;
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
    return "Branch{" + "id=" + id + ", name='" + name + '\''
       + ", initials='" + initials + '\'' + ", parent="
        + (parent != null ? parent.id : null) + ", children=["
        + (children != null ? children.stream().map(d -> d.id + "").collect(Collectors.joining(",")) : null) + "]}";
  }
}

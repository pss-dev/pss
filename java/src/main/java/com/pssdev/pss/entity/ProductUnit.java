package com.pssdev.pss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties
public class ProductUnit {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
  private String note;
  @JoinColumn(name = "father_id")
  @ManyToOne(targetEntity = ProductUnit.class)
  private ProductUnit parent;

  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonIgnore
  private Set<ProductUnit> children = new HashSet<>();

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

  public String getNote() {
    return note;
  }

  public void setNote(String note) {
    this.note = note;
  }

  public ProductUnit getParent() {
    return parent;
  }

  public void setParent(ProductUnit parent) {
    this.parent = parent;
  }

  public Set<ProductUnit> getChildren() {
    return children;
  }

  public void setChildren(Set<ProductUnit> children) {
    this.children = children;
  }

  public boolean isHaveChildren() {
    return this.children == null || this.children.size() == 0 ? false : true;
  }

  @Override
  public String toString() {
    return "ProductUnit{" + "id=" + id + ", name='" + name + '\'' + ", note='" + note + '\'' + '}';
  }
}

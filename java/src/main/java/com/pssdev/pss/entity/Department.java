package com.pssdev.pss.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "t_dept")
@ApiModel("部门信息实例")
public class Department implements Serializable {
  @ApiModelProperty("部门 ID")
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @ApiModelProperty("部门名字")
  @Column
  private String name;

  @ApiModelProperty("部门简称")
  @Column
  private String initials;

  @ApiModelProperty("父部门")
  @ManyToOne
  @JoinColumn(name = "father_id")
  private Department parent;

  @ApiModelProperty("子部门")
  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonIgnore
  private Set<Department> children = new HashSet<>();

  public Department() {
  }

  public Department(Integer id) {
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

  public String getInitials() {
    return initials;
  }

  public void setInitials(String initials) {
    this.initials = initials;
  }

  public Set<Department> getChildren() {
    return children;
  }

  public void setChildren(Set<Department> children) {
    this.children = children;
  }

  public Department getParent() {
    return parent;
  }

  public void setParent(Department parent) {
    this.parent = parent;
  }

  public boolean isHaveChildren() {
    return this.children == null || this.children.size() == 0 ? false : true;
  }

  @Override
  public String toString() {
    return "Department{" + "id=" + id + ", name='" + name + '\'' + ", initials='" + initials + '\'' + ", parent="
        + (parent != null ? parent.id : null) + ", fatherId='" + getParent() + '\'' + "}";
  }
}

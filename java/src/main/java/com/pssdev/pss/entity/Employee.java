package com.pssdev.pss.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Employee implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String name;
  private String account;
  private String password;
  @JoinColumn(name = "branch_id")
  @OneToOne(targetEntity = Branch.class)
  private Branch branch;
  @JoinColumn(name = "department_id")
  @OneToOne(targetEntity = Department.class)
  private Department department;
  @ManyToMany(targetEntity = Role.class)
  private Set<Role> roles;

  public Set<Role> getRoles() {
    return roles;
  }

  public void setRoles(Set<Role> roles) {
    this.roles = roles;
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

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Branch getBranch() {
    return branch;
  }

  public void setBranch(Branch branch) {
    this.branch = branch;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }
}

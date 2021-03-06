package com.pssdev.pss.entity;

import com.pssdev.pss.util.SecurityUtil;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Role implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  @Column
  private String name;

  @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
  @ManyToMany(targetEntity = Permission.class, fetch = FetchType.EAGER)
  private Set<Permission> permissions;

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

  public Set<Permission> getPermissions() {
    return permissions;
  }

  public void setPermissions(Set<Permission> permissions) {
    this.permissions = permissions;
  }

  public boolean isAdministrator() {
    return SecurityUtil.Administrator.equals(name);
  }
}

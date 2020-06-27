package com.pssdev.pss.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Role {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;
   @Column
   private String name;

   @ManyToMany(targetEntity = Permission.class)
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
}

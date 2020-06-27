package com.pssdev.pss.entity;

import javax.persistence.*;

@Entity
public class Permission {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column
   private String resource;

   @Column
   private Integer operator;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getResource() {
      return resource;
   }

   public void setResource(String resource) {
      this.resource = resource;
   }

   public Integer getOperator() {
      return operator;
   }

   public void setOperator(Integer operator) {
      this.operator = operator;
   }

   @Override
   public String toString() {
      return "Permission{" +
         "id=" + id +
         ", resource='" + resource + '\'' +
         ", operator=" + operator +
         '}';
   }
}

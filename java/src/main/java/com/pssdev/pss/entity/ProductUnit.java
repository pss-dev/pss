package com.pssdev.pss.entity;

import javax.persistence.*;

@Entity
public class ProductUnit {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
   private String name;
   private String label;
   @JoinColumn(name = "father_id")
   @ManyToOne(targetEntity = ProductUnit.class)
   private ProductUnit parent;

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

   public String getLabel() {
      return label;
   }

   public void setLabel(String label) {
      this.label = label;
   }

   public ProductUnit getParent() {
      return parent;
   }

   public void setParent(ProductUnit parent) {
      this.parent = parent;
   }

   @Override
   public String toString() {
      return "ProductUnit{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", label='" + label + '\'' +
              '}';
   }
}

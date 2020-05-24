package com.pssdev.pss.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Price {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
   private String name;
   private String label;

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

   @Override
   public String toString() {
      return "Price{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", label='" + label + '\'' +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }

      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      Price price = (Price) o;

      return Objects.equals(id, price.id) &&
              Objects.equals(name, price.name) &&
              Objects.equals(label, price.label);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, name, label);
   }
}

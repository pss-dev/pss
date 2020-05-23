package com.pssdev.pss.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
   private String name;
   private String initials;
   private String specification;
   private String type;
   private String address;
   @Cascade(org.hibernate.annotations.CascadeType.ALL)
   @OneToMany(targetEntity = ProductUnitPrice.class)
   @JoinColumn(name = "product_id")
   private Set<ProductUnitPrice> unit;
   private boolean used;
   private boolean stopPurchase;
   private boolean stop;
   @Transient
   private int actionType;

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

   public String getSpecification() {
      return specification;
   }

   public void setSpecification(String specification) {
      this.specification = specification;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }

   public String getAddress() {
      return address;
   }

   public void setAddress(String address) {
      this.address = address;
   }

   public Set<ProductUnitPrice> getUnit() {
      return unit;
   }

   public void setUnit(Set<ProductUnitPrice> unit) {
      this.unit = unit;
   }

   public boolean isUsed() {
      return used;
   }

   public void setUsed(boolean used) {
      this.used = used;
   }

   public boolean isStopPurchase() {
      return stopPurchase;
   }

   public void setStopPurchase(boolean stopPurchase) {
      this.stopPurchase = stopPurchase;
   }

   public boolean isStop() {
      return stop;
   }

   public void setStop(boolean stop) {
      this.stop = stop;
   }

   public int getActionType() {
      return actionType;
   }

   public void setActionType(int actionType) {
      this.actionType = actionType;
   }

   @Override
   public String toString() {
      return "Product{" +
              "id=" + id +
              ", name='" + name + '\'' +
              ", initials='" + initials + '\'' +
              ", specification='" + specification + '\'' +
              ", type='" + type + '\'' +
              ", address='" + address + '\'' +
              ", unit=" + unit +
              ", used=" + used +
              ", stopPurchase=" + stopPurchase +
              ", stop=" + stop +
              ", actionType=" + actionType +
              '}';
   }
}

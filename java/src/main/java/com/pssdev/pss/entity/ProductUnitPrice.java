package com.pssdev.pss.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class ProductUnitPrice {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
   @OneToMany(targetEntity = PriceValue.class, fetch = FetchType.EAGER)
   @JoinColumn(name = "price_unit_price_id")
   @Cascade(org.hibernate.annotations.CascadeType.ALL)
   private Set<PriceValue> prices;
   @OneToOne(targetEntity = ProductUnit.class)
   @JoinColumn(name = "unit_id")
   private ProductUnit unit;
   @Transient
   private int actionType;
   private boolean defaultUnit;
   private int crate;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Set<PriceValue> getPrices() {
      return prices;
   }

   public void setPrices(Set<PriceValue> prices) {
      this.prices = prices;
   }

   public ProductUnit getUnit() {
      return unit;
   }

   public void setUnit(ProductUnit unit) {
      this.unit = unit;
   }

   public int getActionType() {
      return actionType;
   }

   public void setActionType(int actionType) {
      this.actionType = actionType;
   }

   public boolean isDefault() {
      return defaultUnit;
   }

   public void setDefault(boolean defaultUnit) {
      this.defaultUnit = defaultUnit;
   }

   public int getCrate() {
      return crate;
   }

   public void setCrate(int crate) {
      this.crate = crate;
   }

   @Override
   public String toString() {
      return "ProductUnitPrice{" +
              "id=" + id +
              ", prices=" + prices +
              ", unit=" + unit +
              ", defaultUnit=" + defaultUnit +
              ", crate=" + crate +
              '}';
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      ProductUnitPrice unitPrice = (ProductUnitPrice) o;

      return Objects.equals(id, unitPrice.id);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id);
   }
}

package com.pssdev.pss.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
public class ProductUnitPrice {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id;
   @OneToMany(targetEntity = PriceValue.class)
   @JoinColumn(name = "price_unit_price_id")
   @Cascade(org.hibernate.annotations.CascadeType.ALL)
   private Set<PriceValue> prices;
   @OneToOne(targetEntity = ProductUnit.class)
   @JoinColumn(name = "unit_id")
   private ProductUnit unit;

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

   @Override
   public String toString() {
      return "ProductUnitPrice{" +
              "id=" + id +
              ", prices=" + prices +
              ", unit=" + unit +
              '}';
   }
}

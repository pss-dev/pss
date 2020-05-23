package com.pssdev.pss.entity;

import javax.persistence.*;

@Entity
public class PriceValue {
   @Id
   @GeneratedValue
   private Integer id;
   @OneToOne(targetEntity = Price.class)
   @JoinColumn(name = "price_id")
   private Price price;
   private double value;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Price getPrice() {
      return price;
   }

   public void setPrice(Price price) {
      this.price = price;
   }

   public double getValue() {
      return value;
   }

   public void setValue(double value) {
      this.value = value;
   }

   @Override
   public String toString() {
      return "PriceValue{" +
              "id=" + id +
              ", price=" + price +
              ", value=" + value +
              '}';
   }
}

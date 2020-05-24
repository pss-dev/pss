package com.pssdev.pss.entity;

import javax.persistence.*;
import java.util.Objects;

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

   @Override
   public boolean equals(Object o) {
      if (this == o) {
         return true;
      }

      if (o == null || getClass() != o.getClass()) {
         return false;
      }

      PriceValue that = (PriceValue) o;

      return Objects.equals(id, that.id) &&
              Objects.equals(price, that.price);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, price);
   }
}

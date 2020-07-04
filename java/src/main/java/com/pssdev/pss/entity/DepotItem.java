package com.pssdev.pss.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class DepotItem {
   @Id
   @GeneratedValue
   private Long id;
   @ManyToOne(targetEntity = Depot.class, fetch = FetchType.EAGER)
   private Depot depot;
   @OneToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
   private Product product;
   private Long productCount;

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Depot getDepot() {
      return depot;
   }

   public void setDepot(Depot depot) {
      this.depot = depot;
   }

   public Product getProduct() {
      return product;
   }

   public void setProduct(Product product) {
      this.product = product;
   }

   public Long getProductCount() {
      return productCount;
   }

   public void setProductCount(Long productCount) {
      this.productCount = productCount;
   }
}

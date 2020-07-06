package com.pssdev.pss.entity;

import javax.persistence.*;

@Entity
public class DepotItem {
  @Id
  @GeneratedValue
  private Long id;
  @ManyToOne(targetEntity = Depot.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "depot_id")
  private Depot depot;
  @OneToOne(targetEntity = Product.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "product_id")
  private Product product;
  private int productCount;

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

  public int getProductCount() {
    return productCount;
  }

  public void setProductCount(int productCount) {
    this.productCount = productCount;
  }
}

package com.pssdev.pss.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

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
  @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
  @ManyToMany(targetEntity = ProductUnitPrice.class, fetch = FetchType.EAGER)
  @OrderBy("unit_id asc")
  @JoinColumn(name = "product_id")
  private List<ProductUnitPrice> units;
  private boolean stopPurchase;
  @JoinColumn(name = "father_id")
  @ManyToOne(targetEntity = Product.class)
  private Product parent;
  @Transient
  private int actionType;
  private String identifier;
  @OneToOne(targetEntity = ProductUnit.class)
  @JoinColumn(name = "sell_default_unit_id")
  private ProductUnit sellDefaultUnit;
  @OneToOne(targetEntity = ProductUnit.class)
  @JoinColumn(name = "purchase_default_unit_id")
  private ProductUnit purchaseDefaultUnit;

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

  public List<ProductUnitPrice> getUnits() {
    return units;
  }

  public void setUnits(List<ProductUnitPrice> units) {
    this.units = units;
  }

  public boolean isStopPurchase() {
    return stopPurchase;
  }

  public void setStopPurchase(boolean stopPurchase) {
    this.stopPurchase = stopPurchase;
  }

  public int getActionType() {
    return actionType;
  }

  public void setActionType(int actionType) {
    this.actionType = actionType;
  }

  public Product getParent() {
    return parent;
  }

  public void setParent(Product parent) {
    this.parent = parent;
  }

  public String getIdentifier() {
    return identifier;
  }

  public void setIdentifier(String identifier) {
    this.identifier = identifier;
  }

  public ProductUnit getSellDefaultUnit() {
    return sellDefaultUnit;
  }

  public void setSellDefaultUnit(ProductUnit sellDefaultUnit) {
    this.sellDefaultUnit = sellDefaultUnit;
  }

  public ProductUnit getPurchaseDefaultUnit() {
    return purchaseDefaultUnit;
  }

  public void setPurchaseDefaultUnit(ProductUnit purchaseDefaultUnit) {
    this.purchaseDefaultUnit = purchaseDefaultUnit;
  }

  @Override
  public String toString() {
    return "Product{" + "id=" + id + ", name='" + name + '\'' + ", initials='" + initials + '\'' + ", specification='"
        + specification + '\'' + ", type='" + type + '\'' + ", address='" + address + '\'' + ", units=" + units
        + ", stopPurchase=" + stopPurchase + ", parent=" + parent + ", actionType=" + actionType + ", identifier='"
        + identifier + '\'' + '}';
  }
}

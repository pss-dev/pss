package com.pssdev.pss.entity;

import org.hibernate.annotations.Cascade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pssdev.pss.util.PriceEnum;

import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties
public class Product {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String identifier;
  private String name;
  private String initials;
  private String specification;
  private String type;
  private String address;
  @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
  @OneToMany(targetEntity = ProductUnitPrice.class, fetch = FetchType.EAGER)
  @OrderBy("unit_id asc")
  @JoinColumn(name = "product_id")
  private List<ProductUnitPrice> units;
  private boolean stopPurchase;
  @JoinColumn(name = "father_id")
  @ManyToOne(targetEntity = Product.class)
  private Product parent;
  @Transient
  private int actionType;
  @OneToOne(targetEntity = ProductUnit.class)
  @JoinColumn(name = "sell_default_unit_id")
  private ProductUnit sellDefaultUnit;
  @OneToOne(targetEntity = ProductUnit.class)
  @JoinColumn(name = "purchase_default_unit_id")
  private ProductUnit purchaseDefaultUnit;

  @ApiModelProperty("子商品")
  @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonIgnore
  private Set<Product> children = new HashSet<>();

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

  public Set<Product> getChildren() {
    return children;
  }

  public void setChildren(Set<Product> children) {
    this.children = children;
  }

  public boolean isHaveChildren() {
    return this.children == null || this.children.size() == 0 ? false : true;
  }

  @Override
  public String toString() {
    return "Product{" + "id=" + id + ", name='" + name + '\'' + ", initials='" + initials + '\'' + ", specification='"
        + specification + '\'' + ", type='" + type + '\'' + ", address='" + address + '\'' + ", units=" + units
        + ", stopPurchase=" + stopPurchase + ", parent=" + parent + ", actionType=" + actionType + ", identifier='"
        + identifier + '\'' + '}';
  }

  public static int getColumnCount() {
    return 18;
  }

  public static String templatetLabel(int index) {
    switch (index) {
      case 0:
        return "编号"; // identifier
      case 1:
        return "名称"; // resource
      case 2:
        return "拼音码"; // action
      case 3:
        return "规格"; // employee
      case 4:
        return "型号"; // date
      case 5:
        return "地址"; // message
      case 6:
        return "单位名称"; // identifier
      case 7:
        return "换算率"; // resource
      case 8:
        return PriceEnum.getPriceName(0); // action
      case 9:
        return PriceEnum.getPriceName(1); // employee
      case 10:
        return PriceEnum.getPriceName(2); // date
      case 11:
        return PriceEnum.getPriceName(3); // message
      case 12:
        return PriceEnum.getPriceName(4); // identifier
      case 13:
        return PriceEnum.getPriceName(5); // resource
      case 14:
        return PriceEnum.getPriceName(6); // action
      case 15:
        return PriceEnum.getPriceName(7); // employee
      case 16:
        return PriceEnum.getPriceName(8); // date
      case 17:
        return PriceEnum.getPriceName(9); // message
      default:
        return null;
    }
  }
}

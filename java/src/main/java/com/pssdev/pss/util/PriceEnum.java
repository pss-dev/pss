package com.pssdev.pss.util;

public enum PriceEnum {
  PURCHASEPRICE1(0, "采购价格1"), PURCHASEPRICE2(1, "采购价格2"), PURCHASEPRICE3(2, "采购价格3"), HIGHESTPURCHASEPRICE(3, "最高采购价"),
  SELLPRICE1(4, "销售价格1"), SELLPRICE2(5, "销售价格2"), SELLPRICE3(6, "销售价格3"), RETAILPRICE(7, "零售价"),
  LOWESTSELLPRICE(8, "最低销售价"), HIGHESTSELLPRICE(9, "最低销售价");

  private int type;
  private String name;

  PriceEnum(int type, String name) {
    this.type = type;
    this.name = name;
  }

  public int getType() {
    return type;
  }

  public String getName() {
    return name;
  }

  public static String getPriceName(int price) {
    switch (price) {
      case 0:
        return PURCHASEPRICE1.getName();
      case 1:
        return PURCHASEPRICE2.getName();
      case 2:
        return PURCHASEPRICE3.getName();
      case 3:
        return HIGHESTPURCHASEPRICE.getName();
      case 4:
        return SELLPRICE1.getName();
      case 5:
        return SELLPRICE2.getName();
      case 6:
        return SELLPRICE3.getName();
      case 7:
        return RETAILPRICE.getName();
      case 8:
        return LOWESTSELLPRICE.getName();
      case 9:
        return HIGHESTSELLPRICE.getName();
      default:
        throw new RuntimeException("price is not found! " + price);
    }
  }
}
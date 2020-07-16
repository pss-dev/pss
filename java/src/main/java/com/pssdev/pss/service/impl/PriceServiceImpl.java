package com.pssdev.pss.service.impl;

import com.pssdev.pss.annotation.Audit;
import com.pssdev.pss.dao.PriceDao;
import com.pssdev.pss.entity.Price;
import com.pssdev.pss.service.PriceService;
import com.pssdev.pss.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PriceServiceImpl implements PriceService {
  @Autowired
  private PriceDao priceDao;

  @Audit(value = ResourceEnum.PRICE, actionType = ActionType.MODIFY)
  @Override
  public void modifyPrice(Price price) throws Exception {
    Price old = this.priceDao.get(price.getId());

    if (old != null) {
      old.setLabel(price.getLabel());
      old.setName(price.getName());
      this.priceDao.update(old);
    } else {
      throw new Exception("价格名称不存在");
    }
  }

  @Override
  public List<Price> getPrices() {
    List<Price> prices = this.priceDao.getAll();

    if (prices.size() < 10) {
      insertPrice0(PriceEnum.PURCHASEPRICE1.getName());
      insertPrice0(PriceEnum.PURCHASEPRICE2.getName());
      insertPrice0(PriceEnum.PURCHASEPRICE3.getName());
      insertPrice0(PriceEnum.HIGHESTPURCHASEPRICE.getName());
      insertPrice0(PriceEnum.SELLPRICE1.getName());
      insertPrice0(PriceEnum.SELLPRICE2.getName());
      insertPrice0(PriceEnum.SELLPRICE3.getName());
      insertPrice0(PriceEnum.RETAILPRICE.getName());
      insertPrice0(PriceEnum.LOWESTSELLPRICE.getName());
      insertPrice0(PriceEnum.HIGHESTSELLPRICE.getName());

      prices = this.priceDao.getAll();
    }

    return prices;
  }

  private void insertPrice0(String priceName) {
    Price price = new Price();
    price.setName(priceName);
    price.setLabel(priceName);

    try {
      this.priceDao.insert(price);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

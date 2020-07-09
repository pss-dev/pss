package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.OrderFormDao;
import com.pssdev.pss.entity.OrderForm;
import com.pssdev.pss.entity.OrderFormProduct;
import com.pssdev.pss.entity.DepotItem;
import com.pssdev.pss.model.OrderFormSearchModel;
import com.pssdev.pss.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderFormServiceImpl implements OrderFormService {
  @Autowired
  private OrderFormDao orderFormDao;
  @Autowired
  private DepotService depotService;

  @Override
  public Long insertOrderForm(OrderForm orderForm) throws Exception {
    return this.orderFormDao.insert(orderForm);
  }

  @Override
  public void deleteOrderForm(OrderForm orderForm) {
    if (orderForm != null && orderForm.getId() != null) {
      this.orderFormDao.delete(orderForm);
    }
  }

  @Override
  public void modifyOrderForm(OrderForm orderForm) throws Exception {
    this.orderFormDao.update(orderForm);
  }

  @Override
  public List<OrderForm> getOrderForms() {
    return this.orderFormDao.getAll();
  }

  @Override
  public List<OrderForm> search(OrderFormSearchModel model) {
    assert model != null;
    return this.orderFormDao.search(model);
  }

  @Override
  public OrderForm initOrderForm(OrderForm orderForm) {
    List<DepotItem> depotItems = depotService.getItemsByOrderForm(orderForm);

    for (OrderFormProduct oProduct : orderForm.getProducts()) {
      for (DepotItem ditem : depotItems) {
        if (oProduct.getProduct().getId() == ditem.getProduct().getId()) {
          oProduct.setStock(ditem.getProductCount());
          break;
        }
      }
    }

    return orderForm;
  }
}

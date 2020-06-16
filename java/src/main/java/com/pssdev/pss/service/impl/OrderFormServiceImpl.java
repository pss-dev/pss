package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.OrderFormDao;
import com.pssdev.pss.entity.OrderForm;
import com.pssdev.pss.service.OrderFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class OrderFormServiceImpl implements OrderFormService {
   @Autowired
   private OrderFormDao orderFormDao;

   @Override
   public void insertOrderForm(OrderForm orderForm) throws Exception {
      this.orderFormDao.insert(orderForm);
   }

   @Override
   public void deleteOrderForm(OrderForm orderForm) {
      if(orderForm != null && orderForm.getId() != null) {
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
}

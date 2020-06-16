package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.OrderFormDao;
import com.pssdev.pss.entity.OrderForm;
import org.springframework.stereotype.Component;

@Component
public class OrderFormDaoImpl extends BaseDao<OrderForm, Long> implements OrderFormDao {
   @Override
   protected Class<OrderForm> getClazz() {
      return OrderForm.class;
   }
}

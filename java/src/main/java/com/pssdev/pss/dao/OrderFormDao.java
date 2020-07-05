package com.pssdev.pss.dao;

import com.pssdev.pss.entity.OrderForm;
import com.pssdev.pss.model.OrderFormSearchModel;

import java.util.List;

public interface OrderFormDao extends CURDDao<OrderForm, Long> {
   /**
    * 查询单据
    * @param model
    * @return
    */
   List<OrderForm> search(OrderFormSearchModel model);
}

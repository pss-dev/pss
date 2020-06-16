package com.pssdev.pss.service;

import com.pssdev.pss.entity.OrderForm;

import java.util.List;

public interface OrderFormService {
   /**
    * 插入单据信息
    * @param orderForm
    */
   void insertOrderForm(OrderForm orderForm) throws Exception;

   /**
    * 删除单据信息
    * @param orderForm
    */
   void deleteOrderForm(OrderForm orderForm);

   /**
    * 修改单据信息
    * @param orderForm
    */
   void modifyOrderForm(OrderForm orderForm) throws Exception;

   /**
    * 获得所有单据信息
    * @return
    */
   List<OrderForm> getOrderForms();
}

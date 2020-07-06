package com.pssdev.pss.service;

import com.pssdev.pss.entity.OrderForm;
import com.pssdev.pss.model.OrderFormSearchModel;

import java.util.List;

import javax.persistence.criteria.Order;

public interface OrderFormService {
  /**
   * 插入单据信息
   * 
   * @param orderForm
   */
  Long insertOrderForm(OrderForm orderForm) throws Exception;

  /**
   * 删除单据信息
   * 
   * @param orderForm
   */
  void deleteOrderForm(OrderForm orderForm);

  /**
   * 修改单据信息
   * 
   * @param orderForm
   */
  void modifyOrderForm(OrderForm orderForm) throws Exception;

  /**
   * 获得所有单据信息
   * 
   * @return
   */
  List<OrderForm> getOrderForms();

  /**
   * 查询单据
   * 
   * @param model
   * @return
   */
  List<OrderForm> search(OrderFormSearchModel model);

  /**
   * 初始化库存，只用于草稿表单
   * 
   * @param orderForm
   * @return
   */
  OrderForm initOrderForm(OrderForm orderForm);
}

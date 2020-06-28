package com.pssdev.pss.controller;

import com.pssdev.pss.entity.OrderForm;
import com.pssdev.pss.service.OrderFormService;
import com.pssdev.pss.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderFormController {
  @Autowired
  private OrderFormService orderFormService;

  @PostMapping("/orderForm")
  public List<OrderForm> insertOrderForms(@RequestBody OrderForm orderForm) throws Exception {
    orderFormService.insertOrderForm(orderForm);
    return orderFormService.getOrderForms();
  }

  @DeleteMapping("/orderForm")
  public List<OrderForm> deleteOrderForm(@RequestBody OrderForm orderForm) {
    orderFormService.deleteOrderForm(orderForm);

    return orderFormService.getOrderForms();
  }

  @PutMapping("/orderForm")
  public List<OrderForm> modifyOrderForm(@RequestBody OrderForm orderForm) throws Exception {
    orderFormService.modifyOrderForm(orderForm);

    return orderFormService.getOrderForms();
  }

  @GetMapping("/orderForm")
  public List<OrderForm> getProducts() {
    return orderFormService.getOrderForms();
  }

  @PostMapping("/orderForm/search")
  public List<OrderForm> searchOrderForms(@RequestBody OrderFormSearchModel orderFormSearchModel) throws Exception {
    return null;
  }
}

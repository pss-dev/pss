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
  public void insertOrderForms(@RequestBody OrderForm orderForm) throws Exception {
    orderFormService.insertOrderForm(orderForm);
    System.out.println("================== insertOrderForms ");
  }

  @DeleteMapping("/orderForm")
  public void deleteOrderForm(@RequestBody OrderForm orderForm) {
    orderFormService.deleteOrderForm(orderForm);
  }

  @PutMapping("/orderForm")
  public void modifyOrderForm(@RequestBody OrderForm orderForm) throws Exception {
    orderFormService.modifyOrderForm(orderForm);
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

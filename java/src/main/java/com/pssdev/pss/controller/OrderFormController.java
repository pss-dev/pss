package com.pssdev.pss.controller;

import com.pssdev.pss.entity.*;
import com.pssdev.pss.service.*;
import com.pssdev.pss.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/1.0/")
public class OrderFormController {
  @Autowired
  private OrderFormService orderFormService;
  @Autowired
  private DepotService depotService;

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

  @PutMapping("/orderForm/verify")
  public void verifyOrderForm(@RequestBody OrderForm orderForm) throws Exception {
    // check 'verify' perimission
    if (orderForm.getId() == null) {
      orderFormService.insertOrderForm(orderForm);
    } else {
      orderFormService.modifyOrderForm(orderForm);
    }

    Depot depot = orderForm.getDepot();
    Set<OrderFormProduct> products = orderForm.getProducts();
    List<DepotItem> depotItems = new ArrayList<>();

    for (OrderFormProduct orderFormproduct : products) {
      Product product = orderFormproduct.getProduct();
      int count = orderFormproduct.getCount();

      DepotItem depotItem = new DepotItem();
      depotItem.setDepot(depot);
      depotItem.setProduct(product);
      depotItem.setProductCount(count);

      depotItems.add(depotItem);
    }

    System.out.println("=========== verifyOrderForm " + depotItems.size());
    depotService.putInProducts(depotItems);
  }

  @GetMapping("/orderForm")
  public List<OrderForm> getOrderForms() {
    return orderFormService.getOrderForms();
  }

  @PostMapping("/orderForm/search")
  public List<OrderForm> searchOrderForms(@RequestBody OrderFormSearchModel orderFormSearchModel) throws Exception {
    return null;
  }
}

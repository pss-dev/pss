package com.pssdev.pss.controller;

import com.pssdev.pss.entity.*;
import com.pssdev.pss.service.*;
import com.pssdev.pss.model.*;
import com.pssdev.pss.util.OrderFormStatus;
import com.pssdev.pss.util.OrderFormType;
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
  @Autowired
  private EmployeeService employeeService;

  @PostMapping("/orderForm")
  public Long insertOrderForms(@RequestBody OrderForm orderForm) throws Exception {
    Employee user = employeeService.getCurrentEmployee();
    orderForm.setCreatUser(user);
    return orderFormService.insertOrderForm(orderForm);
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
    Employee user = employeeService.getCurrentEmployee();
    orderForm.setVerifyUser(user);
    orderForm.setStatus(OrderFormStatus.VERIFY);
    // check 'verify' perimission
    if (orderForm.getId() == null) {
      orderForm.setCreatUser(user);
      orderFormService.insertOrderForm(orderForm);
    } else {
      orderFormService.modifyOrderForm(orderForm);
    }

    Depot depot = orderForm.getDepot();
    Set<OrderFormProduct> products = orderForm.getProducts();
    List<DepotItem> depotItems = new ArrayList<>();

    for (OrderFormProduct orderFormproduct : products) {
      Product product = orderFormproduct.getProduct();
      int crate = getUnitCrate(product, orderFormproduct.getUnit());

      if (crate == -1) {
        System.out.println("======  unit not found!");
        continue;
      }

      int count = orderFormproduct.getCount() * crate;
      System.out.println("======  count! " + count);
      DepotItem depotItem = new DepotItem();
      depotItem.setDepot(depot);
      depotItem.setProduct(product);
      depotItem.setProductCount(count);

      depotItems.add(depotItem);
    }

    System.out.println("=========== verifyOrderForm " + depotItems.size());

    if (orderForm.getType() == OrderFormType.PURCHASEFORM || orderForm.getType() == OrderFormType.SALESRETURN) {
      depotService.putInProducts(depotItems);
    } else {
      depotService.putOutProducts(depotItems);
    }

  }

  private int getUnitCrate(Product product, ProductUnit unit) {
    int crate = -1;

    List<ProductUnitPrice> units = product.getUnits();

    for (ProductUnitPrice punit : units) {
      if (punit.getUnit().getId() == unit.getId()) {
        return punit.getCrate();
      }
    }

    return crate;
  }

  @GetMapping("/orderForm")
  public List<OrderForm> getOrderForms() {
    return orderFormService.getOrderForms();
  }

  @PostMapping("/orderForm/search")
  public List<OrderForm> searchOrderForms(@RequestBody OrderFormSearchModel orderFormSearchModel) throws Exception {
    return this.orderFormService.search(orderFormSearchModel);
  }

  @PostMapping("/orderForm/init")
  public OrderForm searchOrderForms(@RequestBody OrderForm orderForm) throws Exception {
    return this.orderFormService.initOrderForm(orderForm);
  }
}

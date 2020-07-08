package com.pssdev.pss.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.pssdev.pss.entity.*;
import com.pssdev.pss.service.*;
import com.pssdev.pss.util.OrderFormStatus;
import com.pssdev.pss.util.OrderFormType;
import com.pssdev.pss.model.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0")
public class RevenueController {
  @Autowired
  private OrderFormService orderFormService;

  @PostMapping("/revenue")
  @RequiresPermissions("*:32768:r")
  public RevenueResultModel getRevenueResu(@RequestBody OrderFormSearchModel orderFormSearchModel) throws Exception {
    List<OrderForm> orderForms = this.orderFormService.search(orderFormSearchModel);
    int purchaseCount = 0;
    int sellCount = 0;
    int purchaseReturnCount = 0;
    int sellReturnCount = 0;
    double expenditure = 0;
    double income = 0;
    double profit = 0;

    for (OrderForm orderForm : orderForms) {
      if (orderForm.getStatus() != OrderFormStatus.VERIFY) {
        System.out.println("==========  sql error");
        continue;
      }

      switch (orderForm.getType()) {
        case OrderFormType.PURCHASEFORM:
          purchaseCount++;
          expenditure += orderForm.getMoney() - orderForm.getWipe();
          break;
        case OrderFormType.SALESFORM:
          sellCount++;
          income += orderForm.getMoney() - orderForm.getWipe();
          break;
        case OrderFormType.PURCHASERETURN:
          purchaseReturnCount++;
          income += orderForm.getMoney() - orderForm.getWipe();
          break;
        case OrderFormType.SALESRETURN:
          sellReturnCount++;
          expenditure += orderForm.getMoney() - orderForm.getWipe();
          break;
      }
    }

    profit = income - expenditure;

    RevenueResultModel result = new RevenueResultModel(purchaseCount, sellCount, purchaseReturnCount, sellReturnCount,
        expenditure, income, profit);
    return result;
  }
}
package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Price;
import com.pssdev.pss.service.PriceService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0")
public class PriceController {
  @Autowired
  private PriceService priceService;

  @PutMapping("/price")
  @RequiresPermissions("*:8:w")
  public List<Price> modifyPrice(@RequestBody Price price) throws Exception {
    priceService.modifyPrice(price);

    return priceService.getPrices();
  }

  @GetMapping("/price")
  @RequiresPermissions("*:8:r")
  public List<Price> getProducts() {
    return priceService.getPrices();
  }
}

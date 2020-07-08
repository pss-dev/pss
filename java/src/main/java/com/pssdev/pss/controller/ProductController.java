package com.pssdev.pss.controller;

import com.pssdev.pss.entity.*;
import com.pssdev.pss.service.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/1.0")
public class ProductController {
  @Autowired
  private ProductService productService;
  @Autowired
  private DepotService depotService;

  @PostMapping("/product")
  @RequiresPermissions("*:2:w")
  public void insertProducts(@RequestBody Product product) throws Exception {
    Integer productId = productService.insertProduct(product);
    Product newProduct = productService.getProduct(productId);

    List<Depot> depots = depotService.getDepots();
    List<DepotItem> depotItems = new ArrayList<>();

    depots.forEach((depot) -> {
      DepotItem depotItem = new DepotItem();
      depotItem.setDepot(depot);
      depotItem.setProduct(newProduct);
      depotItem.setProductCount(0);

      depotItems.add(depotItem);
    });

    depotService.putInProducts(depotItems);
  }

  @DeleteMapping("/product")
  @RequiresPermissions("*:2:d")
  public void deleteProduct(@RequestBody Product product) {
    productService.deleteProduct(product);
  }

  @PutMapping("/product")
  @RequiresPermissions("*:2:w")
  public void modifyProduct(@RequestBody Product product) throws Exception {
    productService.modifyProduct(product);
  }

  @GetMapping("/product")
  @RequiresPermissions("*:2:r")
  public List<Product> getProducts(@RequestParam(required = false) Integer fatherID) {
    return productService.getProducts(fatherID);
  }
}

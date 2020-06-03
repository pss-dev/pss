package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Product;
import com.pssdev.pss.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0/")
public class ProductController {
  @Autowired
  private ProductService productService;

  @PostMapping("/product")
  public void insertProducts(@RequestBody Product product) throws Exception {
    productService.insertProduct(product);
  }

  @DeleteMapping("/product")
  public void deleteProduct(@RequestBody Product product) {
    productService.deleteProduct(product);
  }

  @PutMapping("/product")
  public void modifyProduct(@RequestBody Product product) throws Exception {
    productService.modifyProduct(product);
  }

  @GetMapping("/product")
  public List<Product> getProducts(@RequestParam(required = false) Integer fatherID) {
    return productService.getProducts(fatherID);
  }
}

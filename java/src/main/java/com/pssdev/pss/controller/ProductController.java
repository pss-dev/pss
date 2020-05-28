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
  public List<Product> insertProducts(@RequestBody Product product) throws Exception {
    productService.insertProduct(product);

    return productService.getProducts();
  }

  @DeleteMapping("/product")
  public List<Product> deleteProduct(@RequestBody Product product) {
    productService.deleteProduct(product);

    return productService.getProducts();
  }

  @PutMapping("/product")
  public List<Product> modifyProduct(@RequestBody Product product) throws Exception {
    productService.modifyProduct(product);

    return productService.getProducts();
  }

  @GetMapping("/product")
  public List<Product> getProducts(@RequestParam(required = false) Integer fatherID) {
    return productService.getProducts(fatherID);
  }
}

package com.pssdev.pss.controller;

import com.pssdev.pss.entity.ProductUnit;
import com.pssdev.pss.service.ProductUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0")
public class ProductUnitController {
  @Autowired
  private ProductUnitService productUnitService;

  @PostMapping("/unit")
  public List<ProductUnit> insertProductUnits(@RequestBody ProductUnit productUnit) throws Exception {
    productUnitService.insertProductUnit(productUnit);
    return productUnitService.getProductUnits();
  }

  @DeleteMapping("/unit")
  public List<ProductUnit> deleteProductUnit(@RequestBody ProductUnit productUnit) {
    productUnitService.deleteProductUnit(productUnit);

    return productUnitService.getProductUnits();
  }

  @PutMapping("/unit")
  public List<ProductUnit> modifyProductUnit(@RequestBody ProductUnit productUnit) throws Exception {
    productUnitService.modifyProductUnit(productUnit);

    return productUnitService.getProductUnits();
  }

  @GetMapping("/unit")
  public List<ProductUnit> getProductUnits(@RequestParam(required = false) Integer fatherID) {
    return productUnitService.getProductUnits(fatherID);
  }
}

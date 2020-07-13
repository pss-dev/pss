package com.pssdev.pss.controller;

import com.pssdev.pss.entity.ProductUnit;
import com.pssdev.pss.service.ProductUnitService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0")
public class ProductUnitController {
  @Autowired
  private ProductUnitService productUnitService;

  @PostMapping("/unit")
  @RequiresPermissions("*:4:w")
  public void insertProductUnits(@RequestBody ProductUnit productUnit) throws Exception {
    productUnitService.insertProductUnit(productUnit);
  }

  @PostMapping("/unit/duplicate")
  @RequiresPermissions("*:4:r")
  public boolean checkProductUnitDuplicate(@RequestBody ProductUnit productUnit) throws Exception {
    ProductUnit unit = productUnitService.getUnitByName(productUnit.getName());

    return unit != null && unit.getId() != productUnit.getId();
  }

  @DeleteMapping("/unit")
  @RequiresPermissions("*:4:d")
  public void deleteProductUnit(@RequestBody ProductUnit productUnit) {
    productUnitService.deleteProductUnit(productUnit);
  }

  @PutMapping("/unit")
  @RequiresPermissions("*:4:w")
  public void modifyProductUnit(@RequestBody ProductUnit productUnit) throws Exception {
    productUnitService.modifyProductUnit(productUnit);
  }

  @GetMapping("/unit")
  @RequiresPermissions("*:4:r")
  public List<ProductUnit> getProductUnits(@RequestParam(required = false) Integer fatherID) {
    return productUnitService.getProductUnits(fatherID);
  }
}

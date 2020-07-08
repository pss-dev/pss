package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Depot;
import com.pssdev.pss.entity.DepotItem;
import com.pssdev.pss.service.DepotService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0")
public class DepotController {
  @Autowired
  private DepotService depotService;

  @PostMapping("/depot")
  @RequiresPermissions("*:32:w")
  public void insertDepot(@RequestBody Depot depot) throws Exception {
    depotService.insertDepot(depot);
  }

  @PostMapping("/depot/duplicate")
  @RequiresPermissions("*:32:r")
  public boolean checkDepotDuplicate(@RequestBody Depot depot) throws Exception {
    Depot depo = depotService.getDepotByName(depot.getName());

    return depo == null;
  }

  @DeleteMapping("/depot")
  @RequiresPermissions("*:32:d")
  public void deletedepot(@RequestBody Depot depot) {
    depotService.deleteDepot(depot);
  }

  @PutMapping("/depot")
  @RequiresPermissions("*:32:w")
  public void updateDepot(@RequestBody Depot depot) throws Exception {
    depotService.updateDepot(depot);
  }

  @GetMapping("/depot")
  @RequiresPermissions("*:32:r")
  public List<Depot> getDepots(@RequestParam(required = false) Integer fatherID,
      @RequestParam(required = false) Integer branchID) {
    return depotService.getDepots(fatherID, branchID);
  }

  @GetMapping("/depot/product")
  @RequiresPermissions("*:2:r")
  public DepotItem getInventoryProduct(@RequestParam("depot") int depot, @RequestParam("product") int product) {
    return depotService.inventoryProduct(depot, product);
  }

  @GetMapping("/depot/products")
  @RequiresPermissions("*:2:r")
  public List<DepotItem> getInventoryProducts(@RequestParam("depot") int depot,
      @RequestParam("fatherID") Integer productFatherID) {
    return depotService.inventoryProducts(depot, productFatherID);
  }
}

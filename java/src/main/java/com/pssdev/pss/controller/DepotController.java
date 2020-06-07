package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Depot;
import com.pssdev.pss.service.DepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0")
public class DepotController {
  @Autowired
  private DepotService depotService;

  @PostMapping("/depot")
  public void insertCompanies(@RequestBody Depot depot) throws Exception {
    depotService.insertDepot(depot);
  }

  @DeleteMapping("/depot")
  public void deletedepot(@RequestBody Depot depot) {
    depotService.deleteDepot(depot);
  }

  @PutMapping("/depot")
  public void updateDepot(@RequestBody Depot depot) throws Exception {
    depotService.updateDepot(depot);
  }

  @GetMapping("/depot")
  public List<Depot> getDepots(@RequestParam(required = false) Integer fatherID) {
    return depotService.getDepots(fatherID);
  }
}

package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Company;
import com.pssdev.pss.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0")
public class CompanyController {
  @Autowired
  private CompanyService companyService;

  @PostMapping("/company")
  public void insertCompanies(@RequestBody Company company) throws Exception {
    companyService.insertCompany(company);
  }

  @DeleteMapping("/company")
  public void deleteCompany(@RequestBody Company company) {
    companyService.deleteCompany(company);
  }

  @PutMapping("/company")
  public void modifyCompany(@RequestBody Company company) throws Exception {
    companyService.modifyCompany(company);
  }

  @GetMapping("/company")
  public List<Company> getCompanies(@RequestParam(required = false) Integer fatherID) {
    return companyService.getCompanies(fatherID);
  }
}

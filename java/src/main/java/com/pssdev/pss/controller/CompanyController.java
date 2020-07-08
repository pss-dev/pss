package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Company;
import com.pssdev.pss.service.CompanyService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0")
public class CompanyController {
  @Autowired
  private CompanyService companyService;

  @PostMapping("/company")
  @RequiresPermissions("*:16:w")
  public void insertCompanies(@RequestBody Company company) throws Exception {
    companyService.insertCompany(company);
  }

  @PostMapping("/company/duplicate")
  @RequiresPermissions("*:16:r")
  public boolean checkCompanyDuplicate(@RequestBody Company company) throws Exception {
    Company com = companyService.getCompanyByName(company.getName());

    return com == null;
  }

  @DeleteMapping("/company")
  @RequiresPermissions("*:16:d")
  public void deleteCompany(@RequestBody Company company) {
    companyService.deleteCompany(company);
  }

  @PutMapping("/company")
  @RequiresPermissions("*:16:w")
  public void modifyCompany(@RequestBody Company company) throws Exception {
    companyService.modifyCompany(company);
  }

  @GetMapping("/company")
  @RequiresPermissions("*:16:r")
  public List<Company> getCompanies(@RequestParam(required = false) Integer fatherID,
      @RequestParam(required = false) Integer type) {
    return companyService.getCompanies(fatherID, type);
  }
}

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
   public List<Company> insertCompanies(@RequestBody Company company) throws Exception {
      companyService.insertCompany(company);
      return companyService.getCompanies();
   }

   @DeleteMapping("/company")
   public List<Company> deleteCompany(@RequestBody Company company) {
      companyService.deleteCompany(company);

      return companyService.getCompanies();
   }

   @PutMapping("/company")
   public List<Company> modifyCompany(@RequestBody Company company) throws Exception {
      companyService.modifyCompany(company);

      return companyService.getCompanies();
   }

   @GetMapping("/company")
   public List<Company> getCompanies(@RequestParam(required = false) Integer fatherId) {
      return companyService.getCompanies(fatherId);
   }
}

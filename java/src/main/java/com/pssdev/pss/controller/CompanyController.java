package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Company;
import com.pssdev.pss.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
   @Autowired
   private CompanyService companyService;

   @PostMapping("/api/1.0/company")
   public List<Company> getCompanies(@RequestParam("fatherID") String fatherID, Company company) throws Exception {
      companyService.insertCompany(fatherID, company);

      return companyService.getCompanies();
   }

   @DeleteMapping("/api/1.0/company")
   public List<Company> deleteCompany(Company company) {
      companyService.deleteCompany(company);

      return companyService.getCompanies();
   }

   @PutMapping("/api/1.0/company")
   public List<Company> modifyCompany(Company company) throws Exception {
      companyService.modifyCompany(company);

      return companyService.getCompanies();
   }

   @GetMapping("/api/1.0/company")
   public List<Company> getCompanies() {
      return companyService.getCompanies();
   }

}

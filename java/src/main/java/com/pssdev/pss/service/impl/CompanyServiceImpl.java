package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.CompanyDao;
import com.pssdev.pss.entity.Company;
import com.pssdev.pss.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
   @Autowired
   private CompanyDao companyDao;

   @Override
   @Transactional
   public void insertCompany(String fatherId, Company company) throws Exception {
      Company father = companyDao.getCompany(fatherId);

      if(father == null) {
         throw new Exception("找不所属公司");
      }
      else {
         company.setFatherId(fatherId);
      }

      companyDao.insertCompany(company);
   }

   @Override
   @Transactional
   public void deleteCompany(Company company) {
      companyDao.deleteCompany(company.getId());
   }

   @Override
   @Transactional
   public void modifyCompany(Company company) throws Exception {
      Company oldCompany = companyDao.getCompany(company.getId());

      if(oldCompany == null) {
         throw new Exception("公司不存在");
      }
      else {
         companyDao.modifyCompany(company);
      }
   }

   @Override
   @Transactional
   public List<Company> getCompanies() {
      return companyDao.getCompanies();
   }
}

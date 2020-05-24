package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.CompanyDao;
import com.pssdev.pss.entity.Company;
import com.pssdev.pss.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
   @Autowired
   private CompanyDao companyDao;

   @Override
   @Transactional
   public void insertCompany(Company company) throws Exception {
      if(company.getFather() != null) {
         Company father = companyDao.getCompany(company.getFather().getId());

         if(father == null) {
            throw new Exception("找不所属公司");
         }
      }

      if(companyDao.getCompany(company.getId()) != null) {
         throw new Exception("公司已经存在");
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
         oldCompany.setName(company.getName());
         oldCompany.setInitials(company.getInitials());
         oldCompany.setAddress(company.getAddress());
         oldCompany.setContactPerson(company.getContactPerson());
         oldCompany.setContactPhone(company.getContactPhone());
         oldCompany.setType(company.getType());
         oldCompany.setFather(company.getFather());
         companyDao.modifyCompany(oldCompany);
      }
   }

   @Override
   @Transactional
   public List<Company> getCompanies(Integer fatherId) {
      return fatherId == null ? this.getCompanies() : companyDao.getCompanies(fatherId);
   }

   @Override
   @Transactional
   public List<Company> getCompanies() {
      return companyDao.getCompanies();
   }
}

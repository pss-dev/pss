package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.CompanyDao;
import com.pssdev.pss.entity.Company;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyDaoImpl extends BaseDao implements CompanyDao {
   @Override
   public void insertCompany(Company company) {
      getSession().save(company);
   }

   @Override
   public void deleteCompany(String id) {
      getSession().delete(id);
   }

   @Override
   public void modifyCompany(Company company) {
      getSession().save(company);
   }

   @Override
   public Company getCompany(String id) {
      return getSession().get(Company.class, id);
   }

   @Override
   public List<Company> getCompanies() {
      return getSession().createQuery("from Company").list();
   }
}

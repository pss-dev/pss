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
   public void deleteCompany(Integer id) {
      getSession().createQuery("delete from Company c where c.id =" + id).executeUpdate();
   }

   @Override
   public void deleteCompany(Company company) {
      getSession().delete(company);
   }

   @Override
   public void modifyCompany(Company company) {
      getSession().update(company);
   }

   @Override
   public Company getCompany(Integer id) {
      return getSession().get(Company.class, id);
   }

   @Override
   public List<Company> getCompanies() {
      return getSession().createQuery("from Company").list();
   }

   @Override
   public List<Company> getCompanies(Integer fatherId) {
      return getSession()
              .createQuery("from Company c where c.fatherId = " + fatherId).list();
   }
}

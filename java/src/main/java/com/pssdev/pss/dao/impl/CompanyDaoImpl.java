package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.CompanyDao;
import com.pssdev.pss.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("companyDao")
public class CompanyDaoImpl extends BaseDao<Company, Integer> implements CompanyDao {

   @Override
   public List<Company> getCompanies(Integer fatherId) {
      return getSession()
              .createQuery("from Company c where c.fatherId = " + fatherId).list();
   }

   @Override
   protected Class<Company> getClazz() {
      return Company.class;
   }
}

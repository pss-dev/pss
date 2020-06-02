package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.CompanyDao;
import com.pssdev.pss.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("companyDao")
public class CompanyDaoImpl extends BaseDao<Company, Integer> implements CompanyDao {

   @Override
   public List<Company> getCompanies(Integer fatherId, Integer type) {
      String hql = "from Company c ";

      if(fatherId == -1) {
         hql += "where c.parent is null";
      }
      else {
         hql += "where c.parent.id= " + fatherId;
      }

      if(type != null) {
         hql += " and c.type =" + type;
      }

      return getSession().createQuery(hql).list();
   }

   @Override
   public List<Company> getCompanies(Integer type) {
      String hql = "from Company c where c.type =" + type;

      return getSession().createQuery(hql).list();
   }

   @Override
   protected Class<Company> getClazz() {
      return Company.class;
   }
}

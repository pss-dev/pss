package com.pssdev.pss.dao;

import com.pssdev.pss.entity.Company;

import java.util.List;

public interface CompanyDao extends CURDDao<Company, Integer> {
  /**
   * 获得所有来往公司信息
   * 
   * @return
   * @param fatherId
   */
  List<Company> getCompanies(Integer fatherId, Integer type);

  /**
   * 获得所有来往公司信息
   * 
   * @return
   * @param type
   */
  List<Company> getCompanies(Integer type);

  Company getByName(String name);
}

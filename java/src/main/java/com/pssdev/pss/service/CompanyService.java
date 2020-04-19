package com.pssdev.pss.service;

import com.pssdev.pss.entity.Company;

import java.util.List;

public interface CompanyService {
   /**
    * 插入公司信息
    * @param company
    */
   void insertCompany(Company company) throws Exception;

   /**
    * 删除公司信息
    * @param company
    */
   void deleteCompany(Company company);

   /**
    * 修改公司信息
    * @param company
    */
   void modifyCompany(Company company) throws Exception;

   /**
    * 获得所有用户信息
    * @return
    */
   List<Company> getCompanies();
}

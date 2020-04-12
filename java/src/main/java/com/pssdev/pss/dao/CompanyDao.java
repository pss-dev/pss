package com.pssdev.pss.dao;

import com.pssdev.pss.entity.Company;
import org.springframework.stereotype.Component;

import java.util.List;

public interface CompanyDao {
   /**
    * 插入来往公司信息
    * @param company
    */
   void insertCompany(Company company);

   /**
    * 删除来往公司信息
    * @param id
    */
   void deleteCompany(String id);

   /**
    * 更新来往公司信息
    * @param company
    */
   void modifyCompany(Company company);

   /**
    * 根据id获取来往公司信息
    * @param id
    */
   Company getCompany(String id);

   /**
    * 获得所有来往公司信息
    * @return
    */
   List<Company> getCompanies();
}

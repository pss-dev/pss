package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.DepartmentDao;
import com.pssdev.pss.entity.Department;
import org.springframework.stereotype.Repository;

@Repository("departmentDao")
public class DepartmentDaoImpl extends TreeStructureDaoImpl<Department, Integer> implements DepartmentDao {
  @Override
  protected Class<Department> getClazz() {
    return Department.class;
  }
}

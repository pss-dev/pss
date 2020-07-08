package com.pssdev.pss.dao;

import com.pssdev.pss.entity.Department;

public interface DepartmentDao extends TreeStructureDao<Department, Integer> {
  Department getByName(String name);
}

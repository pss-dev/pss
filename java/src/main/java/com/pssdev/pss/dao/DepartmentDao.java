package com.pssdev.pss.dao;

import com.pssdev.pss.entity.Department;

import java.util.List;

public interface DepartmentDao {
  List<Department> getAllDepartments();

  /**
   * @return new id.
   */
  Integer insertDepartment(Department department);

  Department getDepartment(Integer parentId);

  void deleteDepartment(Department dept);

  void updateDepartment(Department department);
}

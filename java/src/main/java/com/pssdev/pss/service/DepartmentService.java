package com.pssdev.pss.service;

import com.pssdev.pss.entity.Department;

import java.util.List;

public interface DepartmentService extends TreeStructureService<Department> {
  /**
   * get department by id
   */
  Department getDepartment(Integer parentId);

  /**
   * get department by id
   *
   * @param parentId if null get all dept, else get specified dept.
   */
  List<Department> getDepartments(Integer parentId);

  /**
   * get all departments
   */
  List<Department> getDepartments();

  /**
   * insert a department
   *
   * @return new department id.
   */
  int insertDepartment(Department dept);

  /**
   * update department
   */
  void updateDepartment(Department dept);

  /**
   * delete a department
   */
  void deleteDepartment(Department dept);

  /**
   * get department by name
   *
   * @return
   */
  Department getDepartmentByName(String name);
}

package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.DepartmentDao;
import com.pssdev.pss.entity.Department;
import com.pssdev.pss.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {

  @Autowired
  public DepartmentServiceImpl(DepartmentDao departmentDao) {
    this.departmentDao = departmentDao;
  }

  @Transactional(readOnly = true)
  @Override
  public Department getDepartment(Integer parentId) {
    return departmentDao.get(parentId);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Department> getDepartments(Integer parentId) {
    if (parentId == null) {
      return departmentDao.getAll();
    }

    Department department = departmentDao.get(parentId);

    if (department != null) {
      return department.getChildren();
    }

    return null;
  }

  @Transactional(readOnly = true)
  @Override
  public List<Department> getDepartments() {
    return departmentDao.getAll();
  }

  @Transactional
  @Override
  public int insertDepartment(Department department) {
    return departmentDao.insert(department);
  }

  @Transactional
  @Override
  public void updateDepartment(Department department) {
    departmentDao.update(department);
  }

  @Transactional
  @Override
  public void deleteDepartment(Department dept) {
    departmentDao.delete(dept);
  }

  private final DepartmentDao departmentDao;
}

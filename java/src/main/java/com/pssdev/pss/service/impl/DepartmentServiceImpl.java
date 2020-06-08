package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.DepartmentDao;
import com.pssdev.pss.entity.Department;
import com.pssdev.pss.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("departmentService")
@CacheConfig(cacheNames="pss-department")
public class DepartmentServiceImpl implements DepartmentService {

  @Autowired
  public DepartmentServiceImpl(DepartmentDao departmentDao) {
    this.departmentDao = departmentDao;
  }

  @Transactional(readOnly = true)
  @Cacheable(key = "'dept_'+#parentId")
  @Override
  public Department getDepartment(Integer parentId) {
    return departmentDao.get(parentId);
  }

  @Transactional(readOnly = true)
  @Cacheable(key = "'deptAll'+#parentId")
  @Override
  public List<Department> getDepartments(Integer parentId) {
    if (parentId == null) {
      return departmentDao.getAll();
    }
    else if(TOP_FLAG1.equals(parentId) || TOP_FLAG2.equals(parentId)) {
      return departmentDao.getTop();
    }

    Department department = departmentDao.get(parentId);

    if (department != null) {
      return department.getChildren();
    }

    return null;
  }

  @Transactional(readOnly = true)
  @Cacheable(key = "'deptAll-1'")
  @Override
  public List<Department> getTop() {
    return departmentDao.getTop();
  }

  @Transactional(readOnly = true)
  @Cacheable(key = "'deptAll2'")
  @Override
  public List<Department> getDepartments() {
    return departmentDao.getAll();
  }

  @Transactional
  @CacheEvict(allEntries = true)
  @Override
  public int insertDepartment(Department department) {
    return departmentDao.insert(department);
  }

  @Transactional
  @CachePut(key = "'dept_'+#department.getId()")
  @Override
  public void updateDepartment(Department department) {
    departmentDao.update(department);
  }

  @Transactional
  @CacheEvict(allEntries = true)
  @Override
  public void deleteDepartment(Department dept) {
    assert dept.getId() != null;
    departmentDao.delete(dept);
  }

  private final DepartmentDao departmentDao;
}

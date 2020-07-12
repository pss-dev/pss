package com.pssdev.pss.service.impl;

import com.pssdev.pss.annotation.Audit;
import com.pssdev.pss.dao.DepartmentDao;
import com.pssdev.pss.entity.Department;
import com.pssdev.pss.service.DepartmentService;
import com.pssdev.pss.util.ActionType;
import com.pssdev.pss.util.ResourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("departmentService")
@CacheConfig(cacheNames = "pss-department")
public class DepartmentServiceImpl implements DepartmentService {

  @Autowired
  public DepartmentServiceImpl(DepartmentDao departmentDao) {
    this.departmentDao = departmentDao;
  }

  @Transactional(readOnly = true)
  @Cacheable(key = "'dept_'+#p0")
  @Override
  public Department getDepartment(Integer id) {
    return departmentDao.get(id);
  }

  @Transactional(readOnly = true)
  @Cacheable(key = "'deptAll' + (#p0 == null ? '' : #p0)")
  @Override
  public List<Department> getDepartments(Integer parentId) {
    if (parentId == null) {
      return departmentDao.getAll();
    }

    Department department = departmentDao.get(parentId);

    if (department != null) {
      return new ArrayList<>(department.getChildren());
    }

    return null;
  }

  @Transactional(readOnly = true)
  @Cacheable(key = "'deptTop'")
  @Override
  public List<Department> getTop() {
    return departmentDao.getTop();
  }

  @Transactional(readOnly = true)
  @Cacheable(key = "'deptAll'")
  @Override
  public List<Department> getDepartments() {
    return departmentDao.getAll();
  }

  @Audit(ResourceEnum.DEPARTMENT)
  @Transactional
  @CacheEvict(allEntries = true)
  @Override
  public int insertDepartment(Department department) {
    return departmentDao.insert(department);
  }

  @Audit(value = ResourceEnum.DEPARTMENT, actionType = ActionType.MODIFY)
  @Transactional
  @Caching(
     put = {
        @CachePut(key = "'dept_' + #p0.id"),
        @CachePut(key = "'deptName_' + #p0.name")
     },
     evict = {
        @CacheEvict(key = "'deptAll'"),
        @CacheEvict(key = "'deptAll' + #p0?.parent?.id"),
        @CacheEvict(key = "'deptTop'")
     }
  )
  @Override
  public void updateDepartment(Department department) {
    departmentDao.update(department);
  }

  @Audit(value = ResourceEnum.DEPARTMENT, actionType = ActionType.DELETE)
  @Transactional
  @CacheEvict(allEntries = true)
  @Override
  public void deleteDepartment(Department dept) {
    assert dept.getId() != null;
    departmentDao.delete(dept);
  }

  @Transactional
  @Cacheable(key = "'deptName_' + #p0")
  @Override
  public Department getDepartmentByName(String name) {
    return departmentDao.getByName(name);
  }

  private final DepartmentDao departmentDao;
}

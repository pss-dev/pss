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
      return departmentDao.getDepartment(parentId);
   }

   @Transactional(readOnly = true)
   @Override
   public List<Department> getDepartments(Integer parentId) {
      if(parentId == null) {
         return departmentDao.getAllDepartments();
      }

      Department department = departmentDao.getDepartment(parentId);

      if(department != null) {
         return department.getChildren();
      }

      return null;
   }

   @Transactional(readOnly = true)
   @Override
   public List<Department> getDepartments() {
      return departmentDao.getAllDepartments();
   }

   @Transactional
   @Override
   public int insertDepartment(Department department) {
      return departmentDao.insertDepartment(department);
   }

   @Transactional
   @Override
   public void updateDepartment(Department department) {
      departmentDao.updateDepartment(department);
   }

   @Transactional
   @Override
   public void deleteDepartment(Integer id) {
      departmentDao.deleteDepartment(id);
   }

   private final DepartmentDao departmentDao;
}

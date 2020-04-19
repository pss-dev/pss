package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.DepartmentDao;
import com.pssdev.pss.entity.Department;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository("departmentDao")
public class DepartmentDaoImpl extends BaseDao implements DepartmentDao {
   @Override
   public List<Department> getAllDepartments() {
      Session session = getSession();

      CriteriaQuery<Department> query = session.getCriteriaBuilder()
         .createQuery(Department.class);
      query.from(Department.class);

      return session.createQuery(query).list();
   }

   @Override
   public Integer insertDepartment(Department department) {
      Session session = getSession();

      return (Integer) session.save(department);
   }

   @Override
   public Department getDepartment(Integer parentId) {
      return getSession().find(Department.class, parentId);
   }

   @Override
   public void deleteDepartment(Integer id) {
      Department department = getDepartment(id);

      if(department != null) {
         getSession().delete(department);
      }
   }

   @Override
   public void updateDepartment(Department department) {
      getSession().update(department);
   }
}

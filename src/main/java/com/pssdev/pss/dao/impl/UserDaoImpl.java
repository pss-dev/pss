package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.UserDao;
import com.pssdev.pss.entity.User;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

@Repository
public class UserDaoImpl extends BaseDao implements UserDao {
   @Override
   @Transactional
   public List<User> getAllUser() {
      Session session = getSession();
      CriteriaQuery<User> criteriaQuery = session.getCriteriaBuilder().createQuery(User.class);
      criteriaQuery.from(User.class);

      return session.createQuery(criteriaQuery).list();
   }

   @Transactional
   @Override
   public void insertUser(User user) {
      Session session = getSession();
      session.save(user);
   }
}

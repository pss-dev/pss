package com.pssdev.pss.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseDao {
   @Autowired
   private SessionFactory sessionFactory;

   protected Session getSession() {
      if(sessionFactory != null) {
         return sessionFactory.getCurrentSession();
      }

      return null;
   }
}

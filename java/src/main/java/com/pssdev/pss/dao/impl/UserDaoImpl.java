package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.UserDao;
import com.pssdev.pss.entity.User;
import org.springframework.stereotype.Repository;

@Repository("userDao")
public class UserDaoImpl extends BaseDao<User, Long> implements UserDao {
   @Override
   protected Class<User> getClazz() {
      return User.class;
   }
}

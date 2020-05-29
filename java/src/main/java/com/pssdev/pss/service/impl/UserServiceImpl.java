package com.pssdev.pss.service.impl;

import com.pssdev.pss.entity.User;
import com.pssdev.pss.dao.UserDao;
import com.pssdev.pss.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
   @Autowired
   private UserDao userDao;

   @Override
   public User getUser(Long id) {
      return userDao.get(id);
   }

   @Override
   public List<User> getUsers() {
      return userDao.getAll();
   }

   @Override
   public void insertUser(User user) {
      userDao.insert(user);
   }

   @Override
   public void updateUser(User user) {
      userDao.update(user);
   }

   @Override
   public void deleteUser(User user) {
      userDao.delete(user);
   }
}

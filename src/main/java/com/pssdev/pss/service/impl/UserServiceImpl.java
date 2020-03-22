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
   public List<User> getUser(Long id) {
      return userDao.getAllUser();
   }

   @Override
   public List<User> getUsers() {
      return userDao.getAllUser();
   }

   @Override
   public void insertUser(User user) {
      userDao.insertUser(user);
   }

   @Override
   public void updateUser(User user) {
      //userDao.save(user);
   }

   @Override
   public void deleteUser(Long id) {
      //userDao.deleteById(id);
   }
}

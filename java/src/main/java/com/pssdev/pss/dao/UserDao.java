package com.pssdev.pss.dao;

import com.pssdev.pss.entity.User;

import java.util.List;

public interface UserDao {
   List<User> getAllUser();
   void insertUser(User user);
}

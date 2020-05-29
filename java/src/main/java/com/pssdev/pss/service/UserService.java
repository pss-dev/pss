package com.pssdev.pss.service;

import com.pssdev.pss.entity.User;

import java.util.List;

public interface UserService {
   /**
    * get user by id
    * @return
    */
   User getUser(Long id);

   /**
    * get all Users
    * @return
    */
   List<User> getUsers();

   /**
    * insert a user
    * @param user
    */
   void insertUser(User user);

   /**
    * update user
    * @param user
    */
   void updateUser(User user);

   /**
    * delete a user
    */
   void deleteUser(User user);
}

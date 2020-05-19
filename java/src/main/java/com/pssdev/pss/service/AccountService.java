package com.pssdev.pss.service;

import com.pssdev.pss.entity.Account;

import java.util.List;

public interface AccountService {
  /**
   * get Unit by id
   * 
   * @param parentId if null get all dept, else get specified dept.
   */
  List<Account> getAccounts(Integer parentId);

  /**
   * get all Units
   */
  List<Account> getAccounts();

  /**
   * insert a Account
   * 
   * @return new Account id.
   */
  void insertAccount(Account account) throws Exception;

  /**
   * update Account
   */
  void updateAccount(Account account) throws Exception;

  /**
   * delete a Account
   */
  void deleteAccount(Integer account);
}

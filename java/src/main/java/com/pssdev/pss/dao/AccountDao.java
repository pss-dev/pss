package com.pssdev.pss.dao;

import com.pssdev.pss.entity.Account;

import java.util.List;

public interface AccountDao {
  /**
   * 插入账户信息
   * 
   * @param Account
   */
  void insertAccount(Account Account);

  /**
   * 删除账户信息
   * 
   * @param id
   */
  void deleteAccount(Integer id);

  /**
   * 删除账户信息
   * 
   * @param Account
   */
  void deleteAccount(Account account);

  /**
   * 更新账户信息
   * 
   * @param Account
   */
  void modifyAccount(Account account);

  /**
   * 根据id获取账户信息
   * 
   * @param id
   */
  Account getAccount(Integer id);

  /**
   * 获得所有账户信息
   * 
   * @return
   */
  List<Account> getAccounts();

  /**
   * 获得所有账户信息
   * 
   * @return
   * @param fatherId
   */
  List<Account> getAccounts(Integer fatherId);
}

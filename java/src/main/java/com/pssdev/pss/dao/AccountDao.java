package com.pssdev.pss.dao;

import com.pssdev.pss.entity.Account;

import java.util.List;

public interface AccountDao extends CURDDao<Account, Integer> {

  /**
   * 获得所有账户信息
   *
   * @return
   * @param fatherId
   */
  List<Account> getAccounts(Integer fatherId);
}

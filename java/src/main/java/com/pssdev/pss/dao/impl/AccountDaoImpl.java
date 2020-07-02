package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.AccountDao;
import com.pssdev.pss.entity.Account;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("accountDao")
public class AccountDaoImpl extends BaseDao<Account, Integer> implements AccountDao {

  @Override
  public List<Account> getAccounts(Integer fatherId) {
    String hql = "from Account a ";

    if (fatherId == -1) {
      hql += "where a.parent is null";
    } else {
      hql += "where a.parent.id= " + fatherId;
    }

    return getSession().createQuery(hql).list();
  }

  @Override
  protected Class<Account> getClazz() {
    return Account.class;
  }
}

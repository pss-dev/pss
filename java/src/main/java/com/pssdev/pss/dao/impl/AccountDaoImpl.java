package com.pssdev.pss.dao.impl;

import com.pssdev.pss.dao.AccountDao;
import com.pssdev.pss.entity.Account;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AccountDaoImpl extends BaseDao implements AccountDao {
  @Override
  public void insertAccount(Account account) {
    getSession().save(account);
  }

  @Override
  public void deleteAccount(Integer id) {
    getSession().createQuery("delete from Account c where c.id =" + id).executeUpdate();
  }

  @Override
  public void deleteAccount(Account Account) {
    getSession().delete(Account);
  }

  @Override
  public void modifyAccount(Account Account) {
    getSession().update(Account);
  }

  @Override
  public Account getAccount(Integer id) {
    return getSession().get(Account.class, id);
  }

  @Override
  public List<Account> getAccounts() {
    return getSession().createQuery("from Account").list();
  }

  @Override
  public List<Account> getAccounts(Integer fatherId) {
    return getSession().createQuery("from Account c where c.fatherId = " + fatherId).list();
  }
}

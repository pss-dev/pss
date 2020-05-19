package com.pssdev.pss.service.impl;

import com.pssdev.pss.dao.AccountDao;
import com.pssdev.pss.entity.Account;
import com.pssdev.pss.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
  @Autowired
  private AccountDao AccountDao;

  @Override
  @Transactional
  public void insertAccount(Account account) throws Exception {
    if (!StringUtils.isEmpty(account.getFatherId())) {
      Account father = AccountDao.getAccount(account.getFatherId());

      if (father == null) {
        throw new Exception("找不到所属账户");
      }
    }

    if (AccountDao.getAccount(account.getId()) != null) {
      throw new Exception("账户已经存在");
    }

    AccountDao.insertAccount(account);
  }

  @Override
  @Transactional
  public void deleteAccount(Integer id) {
    AccountDao.deleteAccount(id);
  }

  @Override
  @Transactional
  public void updateAccount(Account account) throws Exception {
    Account oldAccount = AccountDao.getAccount(account.getId());

    if (oldAccount == null) {
      throw new Exception("账户不存在");
    } else {
      oldAccount.setName(account.getName());
      oldAccount.setMoney(account.getMoney());
      AccountDao.modifyAccount(oldAccount);
    }
  }

  @Override
  @Transactional
  public List<Account> getAccounts(Integer fatherId) {
    return fatherId == null ? this.getAccounts() : AccountDao.getAccounts(fatherId);
  }

  @Override
  @Transactional
  public List<Account> getAccounts() {
    return AccountDao.getAccounts();
  }
}

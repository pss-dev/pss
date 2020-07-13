package com.pssdev.pss.service.impl;

import com.pssdev.pss.annotation.Audit;
import com.pssdev.pss.dao.AccountDao;
import com.pssdev.pss.entity.Account;
import com.pssdev.pss.service.AccountService;
import com.pssdev.pss.util.ActionType;
import com.pssdev.pss.util.ResourceEnum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
  @Autowired
  private AccountDao accountDao;

  @Audit(ResourceEnum.ACCOUNT)
  @Override
  @Transactional
  public void insertAccount(Account account) throws Exception {
    if (!StringUtils.isEmpty(account.getParent())) {
      Account father = accountDao.get(account.getParent().getId());

      if (father == null) {
        throw new Exception("找不到所属账户");
      }
    }

    if (account.getId() != null && accountDao.get(account.getId()) != null) {
      throw new Exception("账户已经存在");
    }

    accountDao.insert(account);
  }

  @Audit(value = ResourceEnum.ACCOUNT, actionType = ActionType.DELETE)
  @Override
  @Transactional
  public void deleteAccount(Account account) {
    accountDao.delete(account);
  }

  @Audit(value = ResourceEnum.ACCOUNT, actionType = ActionType.MODIFY)
  @Override
  @Transactional
  public void updateAccount(Account account) throws Exception {
    Account oldAccount = accountDao.get(account.getId());

    if (oldAccount == null) {
      throw new Exception("账户不存在");
    } else {
      oldAccount.setName(account.getName());
      oldAccount.setBank(account.getBank());
      oldAccount.setCardholder(account.getCardholder());
      oldAccount.setParent(account.getParent());
      accountDao.update(oldAccount);
    }
  }

  @Override
  @Transactional
  public List<Account> getAccounts(Integer fatherId) {
    return fatherId == null ? this.getAccounts() : accountDao.getAccounts(fatherId);
  }

  @Override
  @Transactional
  public List<Account> getAccounts() {
    return accountDao.getAll();
  }
}

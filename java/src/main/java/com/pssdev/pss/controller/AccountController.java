package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Account;
import com.pssdev.pss.service.AccountService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0")
public class AccountController {
  @Autowired
  private AccountService accountService;

  @PostMapping("/account")
  @RequiresPermissions("*:4096:w")
  public void insertCompanies(@RequestBody Account account) throws Exception {
    accountService.insertAccount(account);
  }

  @DeleteMapping("/account")
  @RequiresPermissions("*:4096:d")
  public void deleteaccount(@RequestBody Account account) {
    accountService.deleteAccount(account);
  }

  @PutMapping("/account")
  @RequiresPermissions("*:4096:w")
  public void updateAccount(@RequestBody Account account) throws Exception {
    accountService.updateAccount(account);
  }

  @GetMapping("/account")
  @RequiresPermissions("*:4096:r")
  public List<Account> getAccounts(@RequestParam(required = false) Integer fatherID) {
    return accountService.getAccounts(fatherID);
  }
}

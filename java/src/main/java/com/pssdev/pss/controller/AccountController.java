package com.pssdev.pss.controller;

import com.pssdev.pss.entity.Account;
import com.pssdev.pss.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/1.0")
public class AccountController {
  @Autowired
  private AccountService accountService;

  @PostMapping("/account")
  public void insertCompanies(@RequestBody Account account) throws Exception {
    accountService.insertAccount(account);
  }

  @DeleteMapping("/account")
  public void deleteaccount(@RequestBody Account account) {
    accountService.deleteAccount(account);
  }

  @PutMapping("/account")
  public void updateAccount(@RequestBody Account account) throws Exception {
    accountService.updateAccount(account);
  }

  @GetMapping("/account")
  public List<Account> getAccounts(@RequestParam(required = false) Integer fatherID) {
    return accountService.getAccounts(fatherID);
  }
}

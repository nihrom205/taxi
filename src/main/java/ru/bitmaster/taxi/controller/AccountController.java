package ru.bitmaster.taxi.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bitmaster.taxi.model.Account;
import ru.bitmaster.taxi.model.Operation;
import ru.bitmaster.taxi.service.AccountService;
import java.util.List;

@RestController
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{account}")
    public Account currentBalance(@PathVariable("account") Long numberAccount) {
        return accountService.findByNumberAccount(numberAccount);
    }

    @PostMapping("/{account}/debet/{money}")
    public void debetMoneyToAmount(@PathVariable("account") Long numberAccount,
                                                 @PathVariable Long money) throws NotFoundException {
        accountService.debetMoney(numberAccount, money);
    }

    @PostMapping("/{account}/credit/{money}")
    public void creditMoneyToAmount(@PathVariable("account") Long numberAccount,
                                 @PathVariable Long money) {
        accountService.creditMoney(numberAccount, money);
    }

    @PostMapping("/{account}/transfer/{distAccount}/{money}")
    public void transferMoneyToAmount(@PathVariable("account") Long numberAccount,
                                    @PathVariable("distAccount") Long distAccount,
                                    @PathVariable Long money) {
        accountService.transfer(numberAccount, distAccount, money);
    }

    @GetMapping("/{account}/debet")
    public List<Operation> reportDebet(@PathVariable("account") Long numberAccount) {
        return accountService.reportDebet(numberAccount);
    }

    @GetMapping("/{account}/credit")
    public List<Operation> reportCredit(@PathVariable("account") Long numberAccount) {
        return accountService.reportCredit(numberAccount);
    }


}

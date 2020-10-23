package ru.bitmaster.taxi.service;

import javassist.NotFoundException;
import ru.bitmaster.taxi.model.Account;
import ru.bitmaster.taxi.model.Operation;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    void create(Account account);
    Optional<Account> findById(Long id);
    Account findByNumberAccount(Long numberAccount);
    void deleteById(Long id);
    void delete(Account account);
    void update(Account account);
    void debetMoney(Long account, Long money) throws AccountNotFoundException;
    void creditMoney(Long account, Long money) throws AccountNotFoundException;
    void transfer(Long numberAccount, Long distAccount, Long money);
    List<Operation> reportDebet(Long numberAccount);
    List<Operation> reportCredit(Long numberAccount);
}

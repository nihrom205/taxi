package ru.bitmaster.taxi.service.Impl;

import ru.bitmaster.taxi.model.Account;
import ru.bitmaster.taxi.model.Operation;
import ru.bitmaster.taxi.service.AccountNotFoundException;
import ru.bitmaster.taxi.service.AccountService;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class AccountServiceStub implements AccountService {

    private Map<Long, Account> storage;

    public void init(Map<Long, Account> map) {
        this.storage = map;
    }

    public void clearStorage() {
        this.storage.clear();
    }

    @Override
    public void create(Account account) {

    }

    @Override
    public Optional<Account> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Account findByNumberAccount(Long numberAccount) {
        Account account = storage.get(numberAccount);
        if (account == null) {
            throw new AccountNotFoundException();
        }
        return account;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void delete(Account account) {

    }

    @Override
    public void update(Account account) {

    }

    @Override
    public void debetMoney(Long account, Long money) throws AccountNotFoundException {
        Account currentAccount = storage.get(account);
        currentAccount.setAmount(currentAccount.getAmount() + money);
        storage.put(account, currentAccount);
    }

    @Override
    public void creditMoney(Long account, Long money) throws AccountNotFoundException {
        Account currentAccount = storage.get(account);
        currentAccount.setAmount(currentAccount.getAmount() - money);
        storage.put(account, currentAccount);
    }

    @Override
    public void transfer(Long numberAccount, Long distAccount, Long money) {
        Account srcAccount = storage.get(numberAccount);
        Account dstAccount = storage.get(distAccount);

    }

    @Override
    public List<Operation> reportDebet(Long numberAccount) {
        return null;
    }

    @Override
    public List<Operation> reportCredit(Long numberAccount) {
        return null;
    }
}

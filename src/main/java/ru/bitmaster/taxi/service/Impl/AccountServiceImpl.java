package ru.bitmaster.taxi.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bitmaster.taxi.model.Account;
import ru.bitmaster.taxi.model.Operation;
import ru.bitmaster.taxi.model.StatusOperation;
import ru.bitmaster.taxi.repo.AccountRepository;
import ru.bitmaster.taxi.service.AccountNotFoundException;
import ru.bitmaster.taxi.service.AccountService;
import ru.bitmaster.taxi.service.OperationService;
import ru.bitmaster.taxi.service.StatusOperationService;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    private final static Long DEBET = 2L;
    private final static Long CREDIT = 1L;
    private final static Long TRANSFER = 3L;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private OperationService operationService;

    @Autowired
    private StatusOperationService statusOperationService;

    @Override
    public void create(Account account) {
        accountRepository.save(account);
    }

    @Override
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    @Override
    public Account findByNumberAccount(Long numberAccount) {
        Optional<Account> optional = accountRepository.findByNumberAccount(numberAccount);
        return optional.orElseThrow(() -> new AccountNotFoundException("Account with id " + numberAccount + " not found"));
    }

    @Override
    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    @Override
    public void delete(Account account) {
        accountRepository.delete(account);
    }

    @Override
    public void update(Account account) {
        accountRepository.save(account);
    }

    @Override
    public void debetMoney(Long account, Long money) throws AccountNotFoundException {
        Optional<Account> optional = accountRepository.findByNumberAccount(account);
        if (!optional.isPresent()) {
            throw new AccountNotFoundException("Account with id " + account + " not found");
        }
        Account curentAccount = optional.get();
        curentAccount.setAmount(curentAccount.getAmount() + money);
        accountRepository.save(curentAccount);

        StatusOperation statusOperation = statusOperationService.findById(DEBET).get();
        Operation operation = new Operation(curentAccount, statusOperation);
        operationService.save(operation);
    }

    @Override
    public void creditMoney(Long numberAccount, Long money) throws AccountNotFoundException {
        Account curentAccount = findAccount(numberAccount);
        Long curentMoney = curentAccount.getAmount();
        if (curentMoney - money < 0) {
            return;
        }
        curentAccount.setAmount(curentAccount.getAmount() - money);
        accountRepository.save(curentAccount);

        StatusOperation statusOperation = statusOperationService.findById(CREDIT).get();
        Operation operation = new Operation(null ,curentAccount, statusOperation);
        operationService.save(operation);
    }

    @Override
    public void transfer(Long srcNumberAccount, Long distNumberAccount, Long money) {
        Account srcAccount = findAccount(srcNumberAccount);
        if (srcAccount.getAmount() - money < 0) {
            return;
        }
        srcAccount.setAmount(srcAccount.getAmount() - money);
        Account distAccount = findAccount(distNumberAccount);
        distAccount.setAmount(distAccount.getAmount() + money);
        accountRepository.saveAll(Arrays.asList(srcAccount, distAccount));

        StatusOperation statusOperation = statusOperationService.findById(TRANSFER).get();
        Operation operation = new Operation(srcAccount, distAccount, statusOperation);
        operationService.save(operation);
    }

    @Override
    public List<Operation> reportDebet(Long numberAccount) {
        Account currentAccount = findAccount(numberAccount);
        return operationService.findByAllDebetAccountToNumber(currentAccount);
    }

    @Override
    public List<Operation> reportCredit(Long numberAccount) {
        Account currentAccount = findAccount(numberAccount);
        return operationService.findByAllCreditAccountToNumber(currentAccount);
    }

    private Account findAccount(Long numberAccount) {
        Optional<Account> optional = accountRepository.findByNumberAccount(numberAccount);
        return optional.orElseThrow(() -> new AccountNotFoundException("Account with id " + numberAccount + " not found"));
    }
}

package ru.bitmaster.taxi.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bitmaster.taxi.model.Account;
import ru.bitmaster.taxi.model.Operation;
import ru.bitmaster.taxi.repo.OperationRepository;
import ru.bitmaster.taxi.service.AccountService;
import ru.bitmaster.taxi.service.OperationService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class OperationServiceImpl implements OperationService {

    @Autowired
    private OperationRepository operationRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public void save(Operation operation) {
        operationRepository.save(operation);
    }

    @Override
    public Optional<Operation> findById(Long id) {
        return operationRepository.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        operationRepository.deleteById(id);
    }

    @Override
    public void delete(Operation operation) {
        operationRepository.delete(operation);
    }

    @Override
    public void update(Operation operation) {
        operationRepository.save(operation);
    }

    @Override
    public List<Operation> findByAllDebetAccountToNumber(Account currentAccount) {
        return operationRepository.findAllByDebitingAccount(currentAccount);
    }

    @Override
    public List<Operation> findByAllCreditAccountToNumber(Account currentAccount) {
        return operationRepository.findAllByCreditingAccount(currentAccount);
    }

    @Override
    public List<Operation> reportToDates(Long numberAccount, LocalDate startDate, LocalDate endDate) {
        Account curentAccount = accountService.findByNumberAccount(numberAccount);
        return operationRepository.findAllByDateCreateLessThanEqualAndDateCreateGreaterThanEqualAndDebitingAccountOrCreditingAccount(endDate, startDate, curentAccount, curentAccount);
    }
}

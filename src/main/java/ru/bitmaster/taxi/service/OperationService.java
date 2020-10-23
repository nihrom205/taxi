package ru.bitmaster.taxi.service;

import ru.bitmaster.taxi.model.Account;
import ru.bitmaster.taxi.model.Operation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OperationService {
    void save(Operation operation);
    Optional<Operation> findById(Long id);
    void deleteById(Long id);
    void delete(Operation operation);
    void update(Operation operation);
    List<Operation> findByAllDebetAccountToNumber(Account currentAccount);
    List<Operation> findByAllCreditAccountToNumber(Account currentAccount);
    List<Operation> reportToDates(Long numberAccount, LocalDate startDate, LocalDate endDate);
}

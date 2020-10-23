package ru.bitmaster.taxi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bitmaster.taxi.model.Account;
import ru.bitmaster.taxi.model.Operation;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {
    List<Operation> findAllByDebitingAccount(Account account);
    List<Operation> findAllByCreditingAccount(Account account);
    List<Operation> findAllByDateCreateLessThanEqualAndDateCreateGreaterThanEqualAndDebitingAccountOrCreditingAccount(LocalDate endDate, LocalDate startDate, Account accountD, Account accountC);
}

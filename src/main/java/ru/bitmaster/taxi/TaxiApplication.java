package ru.bitmaster.taxi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.bitmaster.taxi.model.Account;
import ru.bitmaster.taxi.model.Driver;
import ru.bitmaster.taxi.model.Operation;
import ru.bitmaster.taxi.model.StatusOperation;
import ru.bitmaster.taxi.service.AccountService;
import ru.bitmaster.taxi.service.DriverService;
import ru.bitmaster.taxi.service.OperationService;
import ru.bitmaster.taxi.service.StatusOperationService;

import java.util.Optional;

@SpringBootApplication
public class TaxiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TaxiApplication.class, args);
    }

    @Autowired
    private AccountService accountService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private StatusOperationService statusOperationService;

    @Autowired
    private OperationService operationService;

    @Override
    public void run(String... args) throws Exception {
        Account account1 =  new Account(111L, 100L);
        Account account2 =  new Account(222L, 1000L);

        Driver driver1 = new Driver("Иван", "Иванов");
        driver1.addAccount(account1);
        driver1.addAccount(account2);

        Account account3 = new Account(333L, 4000L);
        Account account4 = new Account(444L, 50L);

        Driver driver2 = new Driver("Петр", "Петров");
        driver2.addAccount(account3);
        driver2.addAccount(account4);

        driverService.create(driver1);
        driverService.create(driver2);

        StatusOperation statusDebet = new StatusOperation("списание");
        StatusOperation statusCredit = new StatusOperation("зачисление");
        StatusOperation statusTransfer = new StatusOperation("перевод");

        statusOperationService.create(statusDebet);
        statusOperationService.create(statusCredit);
        statusOperationService.create(statusTransfer);

        Operation operation1 = new Operation( account1, null, statusCredit);
        Optional<Account> optional = accountService.findById(account1.getId());
        Account currentCccount = optional.get();
        currentCccount.setAmount(currentCccount.getAmount() + 100L);
        accountService.create(currentCccount);
        operationService.save(operation1);
    }
}

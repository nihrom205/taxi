package ru.bitmaster.taxi.service.Impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.bitmaster.taxi.model.Account;
import ru.bitmaster.taxi.service.AccountNotFoundException;
import java.util.HashMap;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(JUnit4.class)
public class AccountServiceImplTest {

    private final AccountServiceStub accountService = new AccountServiceStub();

    @BeforeEach
    public void init() {
        Map<Long, Account> map = new HashMap<>();
        Account account1 =  new Account(111L, 100L);
        Account account2 =  new Account(222L, 1000L);

        map.put(account1.getNumberAccount(), account1);
        map.put(account2.getNumberAccount(), account2);
        accountService.init(map);
    }

    @AfterEach
    public void clear() {
        accountService.clearStorage();
    }

    @Test
    public void getAccoutNumber() {
        Long accoutnNumber = 111L;
        Account actual = accountService.findByNumberAccount(accoutnNumber);
        Account expected = new Account(111L, 100L);

        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void getAccountNotFoundException() {
        Long accoutnNumber = 0L;

        assertThatThrownBy(() -> accountService.findByNumberAccount(accoutnNumber))
                .isInstanceOf(AccountNotFoundException.class);
    }

    @Test
    public void addMoneyToAccount() {
        Long accoutnNumber = 111L;
        Long money = 100L;
        accountService.debetMoney(accoutnNumber, money);
        Account actual = accountService.findByNumberAccount(accoutnNumber);
        Long expectedMoney = 200L;

        assertThat(actual.getAmount()).isEqualTo(expectedMoney);
    }

    @Test
    public void subtractMoneyToAccount() {
        Long accoutnNumber = 111L;
        Long money = 100L;
        accountService.creditMoney(accoutnNumber, money);
        Account actual = accountService.findByNumberAccount(accoutnNumber);
        Long expectedMoney = 0L;

        assertThat(actual.getAmount()).isEqualTo(expectedMoney);
    }

}
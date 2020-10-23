package ru.bitmaster.taxi.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.bitmaster.taxi.model.Account;
import ru.bitmaster.taxi.service.AccountService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountController.class)
public class AccountControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AccountService accountService;

    Account mocAccount = new Account(111L, 100L);
    String accountJson = "{\"id\":\"null\",\"numberAccount\":\"111\",\"amount\":\"100\"}";

    @Test
    public void detailsToAccount() throws Exception {
        Mockito.when(accountService.findByNumberAccount(Mockito.anyLong())).thenReturn(mocAccount);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/111/").accept(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        System.out.println(result.getResponse());

        String exeption = "";

        JSONAssert.assertEquals(exeption, result.getResponse().getContentAsString(), false);
    }
}
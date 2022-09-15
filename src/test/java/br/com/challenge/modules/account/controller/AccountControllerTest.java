package br.com.challenge.modules.account.controller;

import br.com.challenge.modules.account.entity.Account;
import br.com.challenge.modules.account.requests.AccountCreatePostRequestBody;
import br.com.challenge.modules.account.requests.AccountPutRequestBody;
import br.com.challenge.modules.account.service.AccountService;
import br.com.challenge.modules.person.entity.Person;
import br.com.challenge.modules.util.AccountCreator;
import br.com.challenge.modules.util.PersonCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DisplayName("AccountControllerTest")
class AccountControllerTest {

    @InjectMocks
    private AccountController accountController;


    @Mock
    private AccountService accountService;

    @BeforeEach
    void setUp(){
        BDDMockito.when(accountService.createAccount(ArgumentMatchers.any(AccountCreatePostRequestBody.class)))
                .thenReturn(AccountCreator.createAccountCreatePostRequestBody());
    }

    @Test
    @DisplayName("Saved Persist Account When SucessFull")
    void saved_Persist_Account_When_SucessFull() {
        AccountCreatePostRequestBody expected = AccountCreator.createAccountCreatePostRequestBody();
        AccountCreatePostRequestBody account = accountController.createAccount(AccountCreator.createAccountCreatePostRequestBody()).getBody();
        Assertions.assertThat(account).isNotNull();
        Assertions.assertThat(account.getFullName()).isNotNull().isEqualTo(expected.getFullName());
        Assertions.assertThat(account.getCpf()).isNotNull().isEqualTo(expected.getCpf());
    }

    @Test
    @DisplayName("Withdraw Persist Account When SucessFull")
    void withdraw_Persist_Person_When_SucessFull() {
    }

    @Test
    @DisplayName("Deposit Persist Account When SucessFull")
    void deposit_Persist_Person_When_SucessFull() {
    }

    @Test
    @DisplayName("Transfer Persist Account When SucessFull")
    void transfer_Persist_Person_When_SucessFull() {
    }

}